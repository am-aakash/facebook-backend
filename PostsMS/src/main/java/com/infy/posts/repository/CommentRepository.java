package com.infy.posts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infy.posts.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{
	Optional<Comment> findByPostIdAndUserId(String postId, String userId);
	List<Comment> findByPostId(String postId);
}