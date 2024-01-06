package com.infy.posts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infy.posts.entity.Unlike;

public interface UnlikeRepository extends CrudRepository<Unlike, Long> {
	Optional<Unlike> findByPostIdAndUserId(String postId, String userId);
	List<Unlike> findByPostId(String postId);
}
