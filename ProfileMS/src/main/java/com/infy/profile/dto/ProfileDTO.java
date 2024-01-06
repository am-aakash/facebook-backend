package com.infy.profile.dto;

import java.time.LocalDate;
import java.util.Date;
import com.infy.profile.entity.Profile;

public class ProfileDTO {
	private String userId;
	private byte[] profileImage;
	private String bio;
	private byte[] coverImage;
	private String city;
	private String country;
	private String designation;
	private String company;
	private Date updatedAt;
	private String username;
	private String firstName;
	private String lastName;
	private String emailId;
	private LocalDate dateOfBirth;
	private String gender;
	private Date createdAt;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public byte[] getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public byte[] getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(byte[] coverImage) {
		this.coverImage = coverImage;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public static ProfileDTO fromEntityAndUser(Profile profile, UserDTO user) {
		ProfileDTO profileDTO = new ProfileDTO();
		
		profileDTO.setUserId(profile.getUserId());
		profileDTO.setBio(profile.getBio());
		profileDTO.setProfileImage(profile.getProfileImage());
		profileDTO.setCoverImage(profile.getCoverImage());
		profileDTO.setCity(profile.getCity());
		profileDTO.setCountry(profile.getCountry());
		profileDTO.setDesignation(profile.getDesignation());
		profileDTO.setCompany(profile.getCompany());
		profileDTO.setUpdatedAt(profile.getUpdatedAt());
		profileDTO.setUsername(profile.getUsername());
		
		profileDTO.setFirstName(user.getFirstName());
		profileDTO.setLastName(user.getLastName());
		profileDTO.setGender(user.getGender());
		profileDTO.setEmailId(user.getEmailId());
		profileDTO.setDateOfBirth(user.getdateOfBirth());
		profileDTO.setCreatedAt(user.getCreatedAt());
		
		return profileDTO;
	}
}

