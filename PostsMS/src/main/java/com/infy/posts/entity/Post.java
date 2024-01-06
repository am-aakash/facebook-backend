package com.infy.posts.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.infy.posts.dto.PostDTO;

@Entity
public class Post {

	@Id
	private String postId = UUID.randomUUID().toString();
	
	private String userId;

	private String content;

	private String mediaType;

	private String mediaUrl;

	@Lob
	private byte[] mediaContent; // Using byte[] for BLOB data

	private Timestamp timestamp;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
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

	public static Post toEntity(PostDTO postDTO) {
		Post post = new Post();
		post.setPostId(postDTO.getPostId());
		post.setUserId(postDTO.getUserId());
		post.setMediaUrl(postDTO.getMediaUrl());
		post.setContent(postDTO.getContent());
		post.setMediaType(postDTO.getMediaType());
		post.setMediaContent(postDTO.getMediaContent());
		post.setTimestamp(postDTO.getTimestamp());
		return post;
	}
}
