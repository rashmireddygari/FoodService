package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.learning.dto.User;
import com.learning.repo.UserRepo;
import com.learning.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo repo;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	}

	@Override
	public Optional<User> getUserById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public String deleteUserById(long id) {
		repo.deleteById(id);
		return "Delete successful";

	}

	@Override
	public User updateUser(User user) {
		User fromRepo = repo.getById(user.getId());
		fromRepo.setAddresses(user.getAddresses());
		fromRepo.setEmail(user.getEmail());
		fromRepo.setUsername(user.getUsername());
		fromRepo.setPassword(user.getPassword());
		return repo.save(fromRepo);
	}

	@Override
	public List<User> getAllUsersAscOrder() {
		return null;
	}

	@Override
	public List<User> getAllUsersDescOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public boolean existsById(Long userId) {
		// TODO Auto-generated method stub
		return repo.existsById(userId);
	}

}
