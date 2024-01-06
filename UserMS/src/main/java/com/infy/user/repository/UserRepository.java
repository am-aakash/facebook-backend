package com.infy.user.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infy.user.entity.User;

public interface UserRepository extends CrudRepository<User, String>{
	Optional<User> findByUserId(String userId);
	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR CONCAT(u.firstName, ' ', u.lastName) LIKE %:keyword%")
	List<User> getUsersByKeyword(@Param("keyword") String keyword);
}
