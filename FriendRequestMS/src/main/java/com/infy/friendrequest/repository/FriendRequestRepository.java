package com.infy.friendrequest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infy.friendrequest.dto.FriendRequestStatus;
import com.infy.friendrequest.entity.FriendRequest;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, String> {
	Optional<FriendRequest> findBySenderIdAndRecieverIdAndStatus(@Param("senderId") String senderId,
			@Param("recieverId") String recievedId, @Param("status") FriendRequestStatus status);

	List<FriendRequest> findByRecieverIdAndStatus(@Param("recieverId") String recievedId,
			@Param("status") FriendRequestStatus status);
	
	List<FriendRequest> findBySenderIdAndStatus(@Param("senderId") String senderId,
			@Param("status") FriendRequestStatus status);
}
