package com.infy.posts.dto;

import java.sql.Timestamp;

public class UnlikeDTO {
	private Long unlikeId;
	private String postId;
	private String userId;
	private Timestamp timestamp;

	// Getters and setters
	public Long getUnlikeId() {
		return unlikeId;
	}

	public void setUnlikeId(Long unlikeId) {
		this.unlikeId = unlikeId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}