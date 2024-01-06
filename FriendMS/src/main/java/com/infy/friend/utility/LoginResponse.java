package com.infy.friend.utility;

public class LoginResponse {
	private boolean success;
	private String token;
	private String message;
	private String userId;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LoginResponse(boolean success, String token, String userId, String message) {
		super();
		this.success = success;
		this.token = token;
		this.userId = userId;
		this.message = message;
	}
	
	
}
