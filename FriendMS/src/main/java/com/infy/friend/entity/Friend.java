package com.infy.friend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.infy.friend.dto.FriendDTO;

@Entity
public class Friend {
	@Id
	private String friendshipId;

	private String userId;

	private String friendId;

	private boolean isUnfollowed;

	private boolean isBlocked;

	private String category;

	public String getFriendshipId() {
		return friendshipId;
	}

	public void setFriendshipId(String friendshipId) {
		this.friendshipId = friendshipId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public boolean isUnfollowed() {
		return isUnfollowed;
	}

	public void setUnfollowed(boolean isUnfollowed) {
		this.isUnfollowed = isUnfollowed;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public static Friend fromDTO(FriendDTO dto) {
		Friend entity = new Friend();
		entity.setFriendshipId(dto.getFriendshipId());
		entity.setUserId(dto.getUserId());
		entity.setFriendId(dto.getFriendId());
		entity.setUnfollowed(dto.isUnfollowed());
		entity.setBlocked(dto.isBlocked());
		entity.setCategory(dto.getCategory());
		return entity;
	}
}