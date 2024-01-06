package com.infy.user.dto;

import java.time.LocalDate;
import java.util.Date;

import com.infy.user.entity.User;

public class UserDTO {
	private String userId;
	private String username;
	private String firstName;
	private String lastName;
	private String emailId;
	private LocalDate dateOfBirth;
	private String gender;
	private String password;
	private Date createdAt;

	// Getters and setters
	public String getUserId() {
		return userId;
	}

	private void setUserId(String userId) {
		this.userId = userId;
	}

	private void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getdateOfBirth() {
		return dateOfBirth;
	}

	public void setdateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", password=" + password + ", createdAt=" + createdAt + "]";
	}
	
	public static UserDTO fromEntity(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setGender(user.getGender());
		userDTO.setEmailId(user.getEmailId());
		userDTO.setdateOfBirth(user.getdateOfBirth());
		userDTO.setCreatedAt(user.getCreatedAt());
		
		return userDTO;
	}
}
