package com.infy.friendrequest.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.friendrequest.dto.FriendRequestDTO;
import com.infy.friendrequest.dto.FriendRequestStatus;
import com.infy.friendrequest.entity.FriendRequest;
import com.infy.friendrequest.repository.FriendRequestRepository;

@Service
@Transactional
public class FriendRequestServiceImpl implements FriendRequestService {
	@Autowired
	private FriendRequestRepository friendRequestRepository;

	public boolean addRequest(String senderId, String recieverId) throws Exception {
		if (checkIfRequested(senderId, recieverId)) {
			throw new Exception("Service.ALREADY_REQUESTED");
		}
		if (checkIfRequested(recieverId, senderId)) {
			throw new Exception("Service.ALREADY_RECIEVED");
		}
		FriendRequestDTO friendRequest = new FriendRequestDTO();
		friendRequest.setSenderId(senderId);
		friendRequest.setRecieverId(recieverId);
		friendRequest.setStatus(FriendRequestStatus.PENDING);
		friendRequest.setTimestampSent(Timestamp.valueOf(LocalDateTime.now()));
		friendRequest.setRequestId(UUID.randomUUID().toString());

		friendRequestRepository.save(FriendRequest.fromDTO(friendRequest));

		return true;
	}

	public boolean checkIfRequested(String senderId, String recieverId) throws Exception {
		Optional<FriendRequest> optional = friendRequestRepository.findBySenderIdAndRecieverIdAndStatus(senderId,
				recieverId, FriendRequestStatus.PENDING);
		if (optional.isPresent())
			return true;
		return false;
	}

	public String changeStatus(String senderId, String recieverId, String status) throws Exception {
		Optional<FriendRequest> optional = friendRequestRepository.findBySenderIdAndRecieverIdAndStatus(senderId,
				recieverId, FriendRequestStatus.PENDING);
		FriendRequest friendRequest = optional.orElseThrow(() -> new Exception("Service.REQUEST_NOT_FOUND"));
		friendRequest.setStatus(FriendRequestStatus.valueOf(status.toUpperCase()));
		friendRequest.setTimestampAccepted(Timestamp.valueOf(LocalDateTime.now()));
		
		return friendRequest.getRequestId();
	}

	public List<FriendRequestDTO> getRequestsByRecieverId(String recievedId, String status) throws Exception {
		List<FriendRequest> friendRequestList = friendRequestRepository.findByRecieverIdAndStatus(recievedId,  FriendRequestStatus.valueOf(status.toUpperCase()));
		if (friendRequestList.isEmpty()) {
			throw new Exception("Service.REQUEST_NOT_FOUND");
		}
		List<FriendRequestDTO> friendRequests = friendRequestList.stream().map(fr -> FriendRequestDTO.fromEntity(fr))
				.collect(Collectors.toList());
		return friendRequests;
	}
	
	public List<FriendRequestDTO> getRequestsBySenderId(String senderId, String status) throws Exception {
		List<FriendRequest> friendRequestList = friendRequestRepository.findBySenderIdAndStatus(senderId,  FriendRequestStatus.valueOf(status.toUpperCase()));
		if (friendRequestList.isEmpty()) {
			throw new Exception("Service.REQUEST_NOT_FOUND");
		}
		List<FriendRequestDTO> friendRequests = friendRequestList.stream().map(fr -> FriendRequestDTO.fromEntity(fr))
				.collect(Collectors.toList());
		return friendRequests;
	}
}
