package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.dto.User;

public interface UserService {
	public User addUser(User user);
	public Optional<User> getUserById(long id);
	public List<User> getAllUsers();
	public String deleteUserById(long id);
	public User updateUser(User user);
	public List<User> getAllUsersAscOrder();
	public List<User> getAllUsersDescOrder();

	
	
	boolean existsByEmail(String email);

	public Optional<User> findByEmail(String email);
	public boolean existsById(Long userId);
}
