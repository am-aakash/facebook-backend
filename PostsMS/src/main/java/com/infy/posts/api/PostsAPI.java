package com.infy.posts.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.posts.dto.PostDTO;
import com.infy.posts.service.PostsService;
import com.infy.posts.utility.CheckResponse;
import com.infy.posts.utility.CountResponse;
import com.infy.posts.utility.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping(value = "/post")
@Validated
public class PostsAPI {
	private String userUrl = "http://UserMS/user/";
	
	@Autowired
	private PostsService postsService;
	
//	@Autowired
//	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping(value = "/create-post")
	public ResponseEntity<ResponseMessage> createPost(@RequestBody PostDTO post ,@RequestHeader("userId") String userId, @RequestHeader("token") String token) throws Exception {
		CheckResponse tokenCheck = restTemplate.getForObject(this.userUrl + "check-token/" + userId + "/" + token,
				CheckResponse.class);
		if (!tokenCheck.getCheck()) {
			throw new Exception("INVALID_TOKEN");
		}
		post.setUserId(userId);
		postsService.addPost(post);
		
		ResponseMessage response = new ResponseMessage();
		response.setMessage("Added Post");
		response.setSuccess(true);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get-posts/{userId}")
	public ResponseEntity<List<PostDTO>> getPosts(@PathVariable String userId) throws Exception {
		return new ResponseEntity<>(postsService.getPosts(userId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/like/{postId}")
	public ResponseEntity<CheckResponse> likePost(@PathVariable String postId,@RequestHeader("userId") String userId, @RequestHeader("token") String token) throws Exception {
		CheckResponse response = new CheckResponse();
		response.setCheck(postsService.likePost(postId, userId));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/check-like/{postId}")
	public ResponseEntity<CheckResponse> checkLike(@PathVariable String postId,@RequestHeader("userId") String userId, @RequestHeader("token") String token) throws Exception {
		CheckResponse response = new CheckResponse();
		response.setCheck(postsService.checkLike(postId, userId));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/count-like/{postId}")
	public ResponseEntity<CountResponse> countLike(@PathVariable String postId) throws Exception {
		CountResponse response = new CountResponse();
		response.setCount(postsService.likecount(postId));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/delete-like/{postId}")
	public ResponseEntity<CheckResponse> deleteLike(@PathVariable String postId,@RequestHeader("userId") String userId, @RequestHeader("token") String token) throws Exception {
		CheckResponse response = new CheckResponse();
		response.setCheck(postsService.deleteLike(postId, userId));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
