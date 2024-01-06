package com.infy.posts.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Unlike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long unlikeId;

	private String postId;
	
	private String userId;

	@Column
	private Timestamp timestamp;

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
