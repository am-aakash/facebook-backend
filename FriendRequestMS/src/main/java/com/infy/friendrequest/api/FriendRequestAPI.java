package com.infy.friendrequest.api;

import java.util.List;

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

import com.infy.friendrequest.dto.FriendRequestDTO;
import com.infy.friendrequest.service.FriendRequestService;
import com.infy.friendrequest.utitlity.CheckResponse;
import com.infy.friendrequest.utitlity.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping(value = "/friend-request")
@Validated
public class FriendRequestAPI {
	private String userUrl = "http://UserMS/user/";
	private String friendUrl = "http://FriendMS/friend/";

	@Autowired
	private FriendRequestService friendRequestService;

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/add-request/{recieverId}")
	public ResponseEntity<ResponseMessage> addRequest(@PathVariable String recieverId,
			@RequestHeader("userId") String userId, @RequestHeader("token") String token) throws Exception {
		CheckResponse tokenCheck = restTemplate.getForObject(this.userUrl + "check-token/" + userId + "/" + token,
				CheckResponse.class);
		if (!tokenCheck.getCheck()) {
			throw new Exception("API.INVALID_TOKEN");
		}

		boolean requestAdded = friendRequestService.addRequest(userId, recieverId);

		ResponseMessage response = new ResponseMessage();
		response.setMessage(environment.getProperty("API.REQUEST_ADDED"));
		response.setSuccess(requestAdded);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping(value = "/check-request/{senderId}/{recieverId}")
	public ResponseEntity<ResponseMessage> checkRequest(@PathVariable String senderId, @PathVariable String recieverId)
			throws Exception {
		ResponseMessage response = new ResponseMessage();
		response.setSuccess(false);
		response.setMessage(environment.getProperty("API.NO_REQUEST"));

		boolean exists = friendRequestService.checkIfRequested(senderId, recieverId);
		if (exists) {
			response.setMessage(environment.getProperty("API.ALREADY_REQUESTED"));
			response.setSuccess(exists);
		}
		exists = friendRequestService.checkIfRequested(recieverId, senderId);
		if (exists) {
			response.setMessage(environment.getProperty("API.ALREADY_RECIEVED"));
			response.setSuccess(exists);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/change-status/{senderId}/{status}")
	public ResponseEntity<ResponseMessage> checkRequest(@PathVariable String senderId, @PathVariable String status,
			@RequestHeader("userId") String recieverId) throws Exception {

		friendRequestService.changeStatus(senderId, recieverId, status);
		
		if(status.equals("ACCEPTED")) {
			restTemplate.postForObject(this.friendUrl + "add-friends/" + senderId + "/" + recieverId, null, ResponseMessage.class);
			restTemplate.postForObject(this.friendUrl + "add-friends/" + recieverId + "/" + senderId, null, ResponseMessage.class);
		}

		ResponseMessage response = new ResponseMessage();
		response.setMessage(
				environment.getProperty(status.equals("ACCEPTED") ? "API.ACCEPTED_REQUEST" : "API.DECLINED_REQUEST"));
		response.setSuccess(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/get-requests/{status}")
	public ResponseEntity<List<FriendRequestDTO>> getRequests(@PathVariable String status,
			@RequestHeader("userId") String recieverId, @RequestHeader("token") String token) throws Exception {
		CheckResponse tokenCheck = restTemplate.getForObject(this.userUrl + "check-token/" + recieverId + "/" + token,
				CheckResponse.class);
		if (!tokenCheck.getCheck()) {
			throw new Exception("API.INVALID_TOKEN");
		}

		List<FriendRequestDTO> response = friendRequestService.getRequestsByRecieverId(recieverId, status);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-requests-sent/{status}")
	public ResponseEntity<List<FriendRequestDTO>> getRequestsSent(@PathVariable String status,
			@RequestHeader("userId") String senderId, @RequestHeader("token") String token) throws Exception {
		CheckResponse tokenCheck = restTemplate.getForObject(this.userUrl + "check-token/" + senderId + "/" + token,
				CheckResponse.class);
		if (!tokenCheck.getCheck()) {
			throw new Exception("API.INVALID_TOKEN");
		}

		List<FriendRequestDTO> response = friendRequestService.getRequestsBySenderId(senderId, status);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
