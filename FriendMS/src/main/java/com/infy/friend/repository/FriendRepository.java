package com.infy.friend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infy.friend.entity.Friend;

public interface FriendRepository extends CrudRepository<Friend, String> {
	Optional<Friend> findByUserIdAndFriendId(@Param("userId") String userId, @Param("friendId") String friendId);

	List<Friend> findByUserId(String userId);

	List<Friend> findByUserIdAndCategory(String userId, String category);
}
