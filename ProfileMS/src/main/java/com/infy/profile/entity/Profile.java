package com.infy.profile.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.infy.profile.dto.ProfileDTO;

@Entity
public class Profile {
	@Id
	private String userId;
	private String username;
	private byte[] profileImage;
	private String bio;
	private byte[] coverImage;
	private String city;
	private String country;
	private String designation;
	private String company;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	public static Profile fromDTO(ProfileDTO profileDTO) {
		Profile profile = new Profile();
		profile.setUserId(profileDTO.getUserId());
		profile.setUsername(profileDTO.getUsername());
		profile.setBio(profileDTO.getBio());
		profile.setProfileImage(profileDTO.getProfileImage());
		profile.setCoverImage(profileDTO.getCoverImage());
		profile.setCity(profileDTO.getCity());
		profile.setCountry(profileDTO.getCountry());
		profile.setDesignation(profileDTO.getDesignation());
		profile.setCompany(profileDTO.getCompany());
		profile.setUpdatedAt(profileDTO.getUpdatedAt());
		return profile;
	}
}
