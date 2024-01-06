package com.infy.friend.service;

import java.util.List;
import java.util.Set;
import com.infy.friend.dto.FriendDTO;
import com.infy.friend.exception.FriendException;

public interface FriendService {
	public boolean createFriendship(String userId, String friendId)throws FriendException;
	public boolean editCategory(String userId, String friendId, String category) throws FriendException;
	public boolean unFollow(String userId, String friendId) throws FriendException;
	public boolean checkFriendship(String userId, String friendId) throws FriendException;
	public List<FriendDTO> friendsById(String userId) throws FriendException;
	public int friendsCount(String userId) throws FriendException;
	public List<FriendDTO> friendsByCategory(String userId, String category) throws FriendException;
	public Set<String> categoriesByUser(String userId) throws FriendException;
}
