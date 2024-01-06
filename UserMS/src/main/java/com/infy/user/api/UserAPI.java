package com.infy.user.api;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.user.dto.LoginDTO;
import com.infy.user.dto.UserDTO;
import com.infy.user.service.UserService;
import com.infy.user.utility.CheckResponse;
import com.infy.user.utility.LoginResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
@Validated
public class UserAPI {
	private AtomicInteger failedAttempts = new AtomicInteger(0);
    private Instant lastFailureTime = Instant.now();

	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;

	@PostMapping(value = "/register")
	public ResponseEntity<LoginResponse> register(@Valid @RequestBody UserDTO user) throws Exception {
		LoginResponse response = userService.registerUser(user);
		response.setMessage( environment.getProperty("API.REGISTER_SUCCESS"));
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping(value = "/check-username/{username}")
	public ResponseEntity<CheckResponse> checkUsername(@PathVariable String username) throws Exception {
		CheckResponse response = new CheckResponse(userService.checkUniqueUsername(username));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{username}")
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) throws Exception {
		return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String id) throws Exception {
		return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
	}

	@CircuitBreaker(name = "loginService", fallbackMethod = "loginFallback")
	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {		
		if (loginFailsWithin30Seconds()) {
            failedAttempts.incrementAndGet();
            lastFailureTime = Instant.now();
            
            LoginResponse response = new LoginResponse(false, null, null, environment.getProperty("API.LOGIN_FALLBACK"));
    		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
		LoginResponse response = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
		
		if(!response.isSuccess()) {
			failedAttempts.incrementAndGet();
            lastFailureTime = Instant.now();
			response.setMessage(environment.getProperty("API.LOGIN_FAILED"));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		response.setMessage(environment.getProperty("API.LOGIN_SUCCESS"));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/forgot-password/{userId}/{emailId}")
	public ResponseEntity<CheckResponse> forgotPassword(@PathVariable String userId, @PathVariable String emailId) throws Exception{
		return new ResponseEntity<>(new CheckResponse(userService.forgotPassword(userId, emailId)), HttpStatus.OK);
	}
	
	@PutMapping(value = "/change-password")
	public ResponseEntity<CheckResponse> changePassword(@RequestBody UserDTO user) throws Exception {
		
		userService.changePassword(user);
		
		return new ResponseEntity<>(new CheckResponse(true), HttpStatus.OK);
	}
	
	@PutMapping(value = "/update-profile")
	public ResponseEntity<CheckResponse> updateProfile(@RequestBody UserDTO user) throws Exception {
		userService.updateProfile(user);
	
		return new ResponseEntity<>(new CheckResponse(true), HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{keyword}")
	public ResponseEntity<List<UserDTO>> getUsersByKeyword(@PathVariable String keyword) throws Exception {
		return new ResponseEntity<>(userService.getUsersByKeyword(keyword), HttpStatus.OK);
	}
	
	@GetMapping(value = "/check-token/{userId}/{token}")
	public ResponseEntity<CheckResponse> checkToken(@PathVariable String userId, @PathVariable String token) throws Exception{
		return new ResponseEntity<>(new CheckResponse(userService.checkToken(token, userId)), HttpStatus.OK);
	}
	
	public ResponseEntity<LoginResponse> loginFallback(Throwable throwable) {
		LoginResponse response = new LoginResponse(false, null, null, environment.getProperty("API.LOGIN_FALLBACK"));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean loginFailsWithin30Seconds() {
        Instant now = Instant.now();
        Duration duration = Duration.between(lastFailureTime, now);
        return failedAttempts.get() >= 3 && duration.getSeconds() <= 30;
    }

    private boolean isCircuitOpen() {
        return failedAttempts.get() >= 3;
    }
}
