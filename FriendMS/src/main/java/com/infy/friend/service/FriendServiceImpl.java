package com.infy.friend.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.friend.dto.FriendDTO;
import com.infy.friend.entity.Friend;
import com.infy.friend.exception.FriendException;
import com.infy.friend.repository.FriendRepository;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {
	@Autowired
	private FriendRepository friendRepository;

	public boolean createFriendship(String userId, String friendId) throws FriendException {
		Optional<Friend> optional = friendRepository.findByUserIdAndFriendId(userId, friendId);
		if (optional.isPresent())
			throw new FriendException("FRIENDSHIP_EXISTS");

		Friend friend = new Friend();
		friend.setFriendshipId(UUID.randomUUID().toString());
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);
		friendRepository.save(friend);
		return true;
	}

	public boolean editCategory(String userId, String friendId, String category) throws FriendException {
		Optional<Friend> optional = friendRepository.findByUserIdAndFriendId(userId, friendId);
		Friend friend = optional.orElseThrow(() -> new FriendException("NO_FRIENDSHIP"));
		friend.setCategory(category);
		return true;
	}

	public boolean unFollow(String userId, String friendId) throws FriendException {
		Optional<Friend> optional = friendRepository.findByUserIdAndFriendId(userId, friendId);
		Friend friend = optional.orElseThrow(() -> new FriendException("NO_FRIENDSHIP"));
		friend.setUnfollowed(true);
		return true;
	}

	public boolean checkFriendship(String userId, String friendId) throws FriendException {
		Optional<Friend> optional = friendRepository.findByUserIdAndFriendId(userId, friendId);
		if (optional.isPresent())
			return true;
		return false;
	}

	public List<FriendDTO> friendsById(String userId) throws FriendException {
		List<Friend> friends = friendRepository.findByUserId(userId);
		if (friends.isEmpty())
			throw new FriendException("NO_FRIENDSHIPS");

		return friends.stream().map(fr -> FriendDTO.fromEntity(fr)).collect(Collectors.toList());
	}

	public int friendsCount(String userId) throws FriendException {
		List<Friend> friends = friendRepository.findByUserId(userId);
		return friends.size();
	}

	public List<FriendDTO> friendsByCategory(String userId, String category) throws FriendException {
		List<Friend> friends = friendRepository.findByUserIdAndCategory(userId, category);
		if (friends.isEmpty())
			throw new FriendException("NO_FRIENDSHIPS");

		return friends.stream().map(fr -> FriendDTO.fromEntity(fr)).collect(Collectors.toList());
	}

	public Set<String> categoriesByUser(String userId) throws FriendException {
		List<Friend> friends = friendRepository.findByUserId(userId);
		if (friends.isEmpty())
			throw new FriendException("NO_FRIENDSHIPS");

		return friends.stream().filter(fr -> fr.getCategory() != null).map(fr -> fr.getCategory())
				.collect(Collectors.toSet());
	}
}
