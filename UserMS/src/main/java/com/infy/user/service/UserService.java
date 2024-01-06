package com.infy.user.service;

import java.util.List;

import com.infy.user.dto.UserDTO;
import com.infy.user.utility.LoginResponse;

public interface UserService {
	public UserDTO findUser(String userId) throws Exception;

	public UserDTO findUserByUsername(String username) throws Exception;

	public boolean checkUniqueUsername(String username) throws Exception;

	public LoginResponse login(String username, String email) throws Exception;

	public LoginResponse registerUser(UserDTO userDTO) throws Exception;

	public void updateProfile(UserDTO userDTO) throws Exception;
	
	public boolean forgotPassword(String username, String emailId) throws Exception;

	public void changePassword(UserDTO userDTO) throws Exception;

	public List<UserDTO> getUsersByKeyword(String keyword) throws Exception;

	public boolean checkToken(String token, String userId) throws Exception;
}
