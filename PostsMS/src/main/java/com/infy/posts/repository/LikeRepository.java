package com.infy.posts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infy.posts.entity.Like;

public interface LikeRepository extends CrudRepository<Like, Long> {
	Optional<Like> findByPostIdAndUserId(String postId, String userId);
	List<Like> findByPostId(String postId);
	void deleteById(Long id);
}
