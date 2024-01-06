package com.infy.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.user.dto.UserDTO;
import com.infy.user.entity.User;
import com.infy.user.repository.UserRepository;
import com.infy.user.utility.LoginResponse;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDTO findUser(String userId) throws Exception {
		Optional<User> optional = userRepository.findByUserId(userId);
		if (!optional.isPresent())
			throw new Exception("Service.USER_NOT_FOUND");
		return UserDTO.fromEntity(optional.get());
	}

	public UserDTO findUserByUsername(String username) throws Exception {
		Optional<User> optional = userRepository.findByUsername(username);
		if (!optional.isPresent())
			throw new Exception("Service.USER_NOT_FOUND");
		return UserDTO.fromEntity(optional.get());
	}

	public LoginResponse login(String username, String password) throws Exception {
		Optional<User> optional = userRepository.findByUsername(username);
		if (!optional.isPresent())
			throw new Exception("Service.USER_NOT_FOUND");
		User user = optional.get();

		boolean correctCredentials = bcryptEncoder.matches(password, user.getPassword());

		LoginResponse response = new LoginResponse(correctCredentials, user.getUserId(), user.getUserId(), "");
		return response;
	}

	public boolean checkUniqueUsername(String username) throws Exception {
		Optional<User> optional = userRepository.findByUsername(username);
		if (optional.isPresent())
			return false;
		return true;
	}

	public LoginResponse registerUser(UserDTO userDTO) throws Exception {
		boolean validUsername = checkUniqueUsername(userDTO.getUsername());
		if (!validUsername) {
			throw new Exception("Service.USERNAME_PRESENT");
		}

		User user = User.fromDTO(userDTO);
		user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));

		user = userRepository.save(user);

		LoginResponse response = new LoginResponse(true, user.getUserId(), user.getUserId(), "");
		return response;
	}

	public void updateProfile(UserDTO userDTO) throws Exception {
		Optional<User> optional = userRepository.findByUsername(userDTO.getUsername());
		if (!optional.isPresent())
			throw new Exception("Service.USER_NOT_FOUND");
		User user = optional.get();

		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setdateOfBirth(userDTO.getdateOfBirth());
		user.setEmailId(userDTO.getEmailId());
	}
	public boolean forgotPassword(String username, String emailId) throws Exception{
		Optional<User> optional = userRepository.findByUsername(username);
		if (!optional.isPresent())
			throw new Exception("Service.USER_NOT_FOUND");
		
		return optional.get().getEmailId().matches(emailId);
	}

	public void changePassword(UserDTO userDTO) throws Exception {
		Optional<User> optional = userRepository.findByUsername(userDTO.getUsername());
		if (!optional.isPresent())
			throw new Exception("Service.USER_NOT_FOUND");
		User user = optional.get();

		user.setPassword(userDTO.getPassword());
	}

	public List<UserDTO> getUsersByKeyword(String keyword) throws Exception {
		List<User> users = userRepository.getUsersByKeyword(keyword);

		if (users.isEmpty())
			throw new Exception("Service.USERS_NOT_FOUND");

		return users.stream().map(user -> UserDTO.fromEntity(user)).collect(Collectors.toList());
	}

	public boolean checkToken(String token, String userId) throws Exception {
		Optional<User> optional = userRepository.findByUserId(userId);
		if (!optional.isPresent())
			throw new Exception("Service.USER_NOT_FOUND");
		return (optional.get().getUserId().matches(token));
	}

}
