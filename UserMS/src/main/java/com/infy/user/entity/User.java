package com.infy.user.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.infy.user.dto.UserDTO;

@Entity
public class User {
	@Id
	private String userId = UUID.randomUUID().toString();
	private String username;
	private String firstName;
	private String lastName;
	private String emailId;
	private LocalDate dateOfBirth;
	private String gender;
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = Timestamp.valueOf(LocalDateTime.now());

	// Getters and setters
	public String getUserId() {
		return userId;
	}

//	private void setUserId(UUID userId) {
//		this.userId = userId;
//	}

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
	
//	private void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", password=" + password + ", createdAt=" + createdAt + "]";
	}

	public static User fromDTO(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setGender(userDTO.getGender());
		user.setEmailId(userDTO.getEmailId());
		user.setdateOfBirth(userDTO.getdateOfBirth());
		
		return user;
	}
}
