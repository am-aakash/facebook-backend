package com.infy.profile.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.profile.dto.ProfileDTO;
import com.infy.profile.dto.UserDTO;
import com.infy.profile.entity.Profile;
import com.infy.profile.repository.ProfileRepository;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	ProfileRepository profileRepository;

	public String addProfile(ProfileDTO profile, UserDTO user) throws Exception {
		profile = ProfileDTO.fromEntityAndUser(Profile.fromDTO(profile), user);
		Profile profileEntity = Profile.fromDTO(profile);

		profileEntity = profileRepository.save(profileEntity);

		return profileEntity.getUsername();
	}

	public void editProfile(ProfileDTO profile) throws Exception {
		Optional<Profile> optional = profileRepository.findById(profile.getUserId());
		if (!optional.isPresent())
			throw new Exception("Service.USER_NOT_FOUND");
		Profile profileEntity = optional.get();
		if (profile.getBio() != null)
			profileEntity.setBio(profile.getBio());
		if (profile.getCity() != null)
			profileEntity.setCity(profile.getCity());
		if (profile.getCountry() != null)
			profileEntity.setCountry(profile.getCountry());
		if (profile.getDesignation() != null)
			profileEntity.setDesignation(profile.getDesignation());
		if (profile.getCompany() != null)
			profileEntity.setCompany(profile.getCompany());
		if(profile.getProfileImage() != null)
			profileEntity.setProfileImage(profile.getProfileImage());
		if(profile.getCoverImage() != null)
			profileEntity.setCoverImage(profile.getCoverImage());
		profileEntity.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
	}

	public ProfileDTO getProfileById(UserDTO user) throws Exception {
		Optional<Profile> optional = profileRepository.findById(user.getUserId());
		if (!optional.isPresent())
			throw new Exception("Service.USER_NOT_FOUND");
		
		return ProfileDTO.fromEntityAndUser(optional.get(), user);
	}

	public List<ProfileDTO> getProfilesByKeyword(List<UserDTO> users) throws Exception {
		if(users.isEmpty())  throw new Exception("API.USERS_NOT_FOUND");
		return users.stream().map((UserDTO user) -> {
			Optional<Profile> optional = profileRepository.findById(user.getUserId());
			return ProfileDTO.fromEntityAndUser(optional.get(), user);
		}).collect(Collectors.toList());
	}
}
