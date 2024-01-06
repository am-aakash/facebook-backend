package com.infy.profile.service;

import java.util.List;

import com.infy.profile.dto.ProfileDTO;
import com.infy.profile.dto.UserDTO;

public interface ProfileService {
	public String addProfile(ProfileDTO profile, UserDTO user) throws Exception;
	
	public void editProfile(ProfileDTO profile) throws Exception;
	
	public ProfileDTO getProfileById(UserDTO user) throws Exception;
	
	public List<ProfileDTO> getProfilesByKeyword(List<UserDTO> users) throws Exception;
}
