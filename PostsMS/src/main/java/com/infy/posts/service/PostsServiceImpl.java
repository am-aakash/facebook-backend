package com.infy.posts.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.posts.dto.PostDTO;
import com.infy.posts.entity.Like;
import com.infy.posts.entity.Post;
import com.infy.posts.repository.LikeRepository;
import com.infy.posts.repository.PostsRepository;

@Service
@Transactional
public class PostsServiceImpl implements PostsService {

	@Autowired
	private PostsRepository postsRepository;

	@Autowired
	private LikeRepository likeRepository;

	public PostDTO addPost(PostDTO post) throws Exception {
		post.setPostId(UUID.randomUUID().toString());
		return PostDTO.toDTO(postsRepository.save(Post.toEntity(post)));
	}

	public PostDTO getPost(String postId) throws Exception {
		Optional<Post> optional = postsRepository.findById(postId);
		if (optional.isEmpty())
			throw new Exception("NO_POST_FOUND");

		return PostDTO.toDTO(optional.get());
	}

	public List<PostDTO> getPosts(String userId) throws Exception {
		List<Post> posts = postsRepository.findByUserId(userId);
		if (posts.isEmpty())
			throw new Exception("NO_POST_FOUND");

		return posts.stream().map(p -> PostDTO.toDTO(p)).collect(Collectors.toList());
	}

	public boolean likePost(String postId, String userId) throws Exception {
		if(checkLike(postId, userId)) {
			return false;
		}
		Like like = new Like();
		like.setUserId(userId);
		like.setPostId(postId);
		like.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		likeRepository.save(like);
		return true;
	}

	public boolean checkLike(String postId, String userId) throws Exception {
		Optional<Like> optional = likeRepository.findByPostIdAndUserId(postId, userId);
		if(optional.isEmpty()) return false;
		return true;
	}

	public Integer likecount(String postId) throws Exception {
		List<Like> likes = likeRepository.findByPostId(postId);
		return likes.size();
	}

	public boolean deleteLike(String postId, String userId) throws Exception {
		Optional<Like> optional = likeRepository.findByPostIdAndUserId(postId, userId);
		if(optional.isEmpty()) return false;
		likeRepository.delete(optional.get());
		return true;
	}
}
