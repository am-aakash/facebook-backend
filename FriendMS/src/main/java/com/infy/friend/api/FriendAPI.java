package com.infy.friend.api;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.friend.dto.FriendDTO;
import com.infy.friend.exception.FriendException;
import com.infy.friend.service.FriendService;
import com.infy.friend.utility.CheckResponse;
import com.infy.friend.utility.CountResponse;
import com.infy.friend.utility.ResponseMessage;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/friend")
@Validated
public class FriendAPI {
	private String tokenCheckUrl = "http://UserMS/user/check-token/";
	private String invalidToken = "INVALID_TOKEN";
	
	@Autowired
	private FriendService friendService;

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/add-friends/{userId}/{friendId}")
	public ResponseEntity<ResponseMessage> addFriendship(@PathVariable String userId, @PathVariable String friendId)
			throws FriendException {
		boolean friendsAdded = friendService.createFriendship(userId, friendId);

		ResponseMessage response = new ResponseMessage();
		response.setMessage(environment.getProperty("FRIENDSHIP_ADDED"));
		response.setSuccess(friendsAdded);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/category/{friendId}/{category}")
	public ResponseEntity<ResponseMessage> addCategory(@PathVariable String friendId, @PathVariable String category,
			@RequestHeader("userId") String userId, @RequestHeader("token") String token) throws FriendException {
		CheckResponse tokenCheck = restTemplate.getForObject(this.tokenCheckUrl + userId + "/" + token,
				CheckResponse.class);
		if (tokenCheck!=null && !tokenCheck.getCheck()) {
			throw new FriendException(invalidToken);
		}

		boolean categoryChanged = friendService.editCategory(userId, friendId, category);

		ResponseMessage response = new ResponseMessage();
		response.setMessage(environment.getProperty("CATEGORY_ADDED"));
		response.setSuccess(categoryChanged);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-friends")
	public ResponseEntity<List<FriendDTO>> getFriends(@RequestHeader("userId") String userId,
			@RequestHeader("token") String token) throws FriendException {
		CheckResponse tokenCheck = restTemplate.getForObject(this.tokenCheckUrl + userId + "/" + token,
				CheckResponse.class);
		if (tokenCheck!=null && !tokenCheck.getCheck()) {
			throw new FriendException(invalidToken);
		}
		List<FriendDTO> response = friendService.friendsById(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/check-friendship/{senderId}/{recieverId}")
	public ResponseEntity<ResponseMessage> checkRequest(@PathVariable String senderId, @PathVariable String recieverId)
			throws FriendException {
		ResponseMessage response = new ResponseMessage();
		response.setSuccess(false);
		response.setMessage("Not a friend");

		boolean exists = friendService.checkFriendship(senderId, recieverId);
		if (exists) {
			response.setMessage("Friend");
			response.setSuccess(exists);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-friends-count/{userId}")
	public ResponseEntity<CountResponse> getFriendsCount(@PathVariable String userId) throws FriendException {
		CountResponse response = new CountResponse();
		response.setCount(friendService.friendsCount(userId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-categories/{userId}")
	public ResponseEntity<Set<String>> getCategories(@PathVariable String userId) throws FriendException {
		return new ResponseEntity<>(friendService.categoriesByUser(userId), HttpStatus.OK);
	}

	@GetMapping(value = "/get-friends-by/{category}")
	public ResponseEntity<List<FriendDTO>> getFriends(@PathVariable String category,
			@RequestHeader("userId") String userId, @RequestHeader("token") String token) throws FriendException {
		CheckResponse tokenCheck = restTemplate.getForObject(this.tokenCheckUrl + userId + "/" + token,
				CheckResponse.class);
		if (tokenCheck!=null && !tokenCheck.getCheck()) {
			throw new FriendException(invalidToken);
		}
		List<FriendDTO> response = friendService.friendsByCategory(userId, category);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
