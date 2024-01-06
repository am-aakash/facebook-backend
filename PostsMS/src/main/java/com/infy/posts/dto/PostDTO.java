package com.infy.posts.dto;

import java.sql.Timestamp;

import com.infy.posts.entity.Post;

public class PostDTO {
	private String postId;
	private String userId;
	private String content;
	private String mediaType;
	private String mediaUrl;
	private byte[] mediaContent;
	private Timestamp timestamp;

	public static PostDTO toDTO(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setPostId(post.getPostId());
		postDTO.setUserId(post.getUserId());
		postDTO.setContent(post.getContent());
		postDTO.setMediaType(post.getMediaType());
		postDTO.setMediaUrl(post.getMediaUrl());
		postDTO.setMediaContent(post.getMediaContent());
		postDTO.setTimestamp(post.getTimestamp());
		return postDTO;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public byte[] getMediaContent() {
		return mediaContent;
	}

	public void setMediaContent(byte[] mediaContent) {
		this.mediaContent = mediaContent;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
