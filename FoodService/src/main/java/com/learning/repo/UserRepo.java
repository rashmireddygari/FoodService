package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);

	public Optional<User> findByEmail(String email);
	public Optional<User> findByUsername(String username);
}
