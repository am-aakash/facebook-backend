package com.infy.posts.service;

import java.util.List;

import com.infy.posts.dto.PostDTO;

public interface PostsService {
	public PostDTO addPost(PostDTO post) throws Exception;
	public PostDTO getPost(String postId) throws Exception;
	public List<PostDTO> getPosts(String userId) throws Exception;
	
	public boolean likePost(String postId, String userId) throws Exception;
	public boolean checkLike(String postId, String userId) throws Exception;
	public Integer likecount(String postId) throws Exception;
	public boolean deleteLike(String postId, String userid) throws Exception;
}
