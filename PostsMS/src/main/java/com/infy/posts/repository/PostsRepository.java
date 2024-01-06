package com.infy.posts.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.infy.posts.entity.Post;

public interface PostsRepository extends CrudRepository<Post, String> {
	List<Post> findByUserId(String userId);
	
}
