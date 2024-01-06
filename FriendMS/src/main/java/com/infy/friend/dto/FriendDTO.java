package com.infy.friend.dto;

import javax.validation.constraints.NotNull;

import com.infy.friend.entity.Friend;

public class FriendDTO {
	private String friendshipId;
	@NotNull
    private String userId;
	@NotNull
    private String friendId;
    private boolean isUnfollowed;
    private boolean isBlocked;
    private String category;    

    public static FriendDTO fromEntity(Friend entity) {
        FriendDTO dto = new FriendDTO();
        dto.setFriendshipId(entity.getFriendshipId());
        dto.setUserId(entity.getUserId());
        dto.setFriendId(entity.getFriendId());
        dto.setUnfollowed(entity.isUnfollowed());
        dto.setBlocked(entity.isBlocked());
        dto.setCategory(entity.getCategory());
        return dto;
    }

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
}
