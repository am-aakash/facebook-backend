package com.infy.profile.api;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.infy.profile.dto.ProfileDTO;
import com.infy.profile.dto.UserDTO;
import com.infy.profile.service.ProfileService;
import com.infy.profile.utility.CheckResponse;
import com.infy.profile.utility.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping(value = "/profile")
@Validated
public class ProfileAPI {

	private String url = "http://UserMS/user/";

	@Autowired
	private ProfileService profileService;

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/add")
	public ResponseEntity<ResponseMessage> addProfile(@Valid @RequestBody ProfileDTO profile,
			@RequestHeader("userId") String userId, @RequestHeader("token") String token) throws Exception {
		CheckResponse tokenCheck = restTemplate.getForObject(this.url + "check-token/" + userId + "/" + token,
				CheckResponse.class);
		if (!tokenCheck.getCheck()) {
			throw new Exception("API.INVALID_TOKEN");
		}
		UserDTO user = restTemplate.getForObject(this.url + "id/" + profile.getUserId(), UserDTO.class);
		String message = environment.getProperty("API.ADD_PROFILE_SUCCESS") + " : "
				+ profileService.addProfile(profile, user);
		ResponseMessage response = new ResponseMessage(message);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/edit")
	public ResponseEntity<CheckResponse> editProfile(@Valid @RequestBody ProfileDTO profile,
			@RequestHeader("userId") String userId, @RequestHeader("token") String token) throws Exception {
		CheckResponse tokenCheck = restTemplate.getForObject(this.url + "check-token/" + userId + "/" + token,
				CheckResponse.class);
		if (!tokenCheck.getCheck()) {
			throw new Exception("API.INVALID_TOKEN");
		}
		restTemplate.put(this.url + "update-profile", profile);
		profileService.editProfile(profile);

		CheckResponse response = new CheckResponse();
		response.setCheck(true);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/get-by-id/{id}")
	public ResponseEntity<ProfileDTO> getProfile(@PathVariable String id, @RequestHeader("userId") String userId,
			@RequestHeader("token") String token) throws Exception {
		CheckResponse tokenCheck = restTemplate.getForObject(this.url + "check-token/" + userId + "/" + token,
				CheckResponse.class);
		if (!tokenCheck.getCheck()) {
			throw new Exception("API.INVALID_TOKEN");
		}
		UserDTO user = restTemplate.getForObject(this.url + "id/" + id, UserDTO.class);
		return new ResponseEntity<>(profileService.getProfileById(user), HttpStatus.OK);
	}

	@GetMapping(value = "/search-profile/{keyword}")
	public ResponseEntity<List<ProfileDTO>> searchProfile(@PathVariable String keyword) throws Exception {
		if(keyword.isBlank()) throw new Exception("API.USERS_NOT_FOUND");
		UserDTO[] users = restTemplate.getForObject(this.url + "search/" + keyword, UserDTO[].class);
		return new ResponseEntity<>(profileService.getProfilesByKeyword(Arrays.asList(users)), HttpStatus.OK);
	}

}
