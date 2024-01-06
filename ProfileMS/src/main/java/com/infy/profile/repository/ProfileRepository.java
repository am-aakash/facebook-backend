package com.infy.profile.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.profile.entity.Profile;

public interface ProfileRepository extends CrudRepository<Profile, String>{
	
}
