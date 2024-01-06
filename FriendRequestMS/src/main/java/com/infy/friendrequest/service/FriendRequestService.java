package com.infy.friendrequest.service;
import java.util.List;
import com.infy.friendrequest.dto.FriendRequestDTO;

public interface FriendRequestService {
	public boolean addRequest(String senderId, String recieverId) throws Exception;
	public boolean checkIfRequested(String senderId, String recieverId) throws Exception;
	public String changeStatus(String senderId, String recieverId, String status) throws Exception;
	public List<FriendRequestDTO> getRequestsByRecieverId(String recieverId, String status) throws Exception;
	public List<FriendRequestDTO> getRequestsBySenderId(String senderId, String status) throws Exception;
}
