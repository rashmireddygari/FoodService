package com.learning.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.User;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.response.UserResponse;
import com.learning.repo.FoodItemRepo;
import com.learning.repo.RoleRepo;
import com.learning.service.UserService;
import com.learning.utils.Builder;

@RestController
@RequestMapping("/api/users")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@Autowired
	FoodItemRepo foodItemRepo;
	@Autowired
	RoleRepo roleRepo;

	@GetMapping("/")
	public ResponseEntity<?> getAllUsers() {
		List<User> list = userService.getAllUsers();
		List<UserResponse> response = new ArrayList<UserResponse>();
		list.forEach(e -> {
			response.add(Builder.buildUserResponse(e));
		});
		if (response.size() > 0) {
			return ResponseEntity.ok(response);
		} else {
			throw new NoDataFoundException("No records found");
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {
		User user = userService.getUserById(userId).orElseThrow(() -> new NoDataFoundException("User Not Found"));
		UserResponse response = Builder.buildUserResponse(user);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> updatePerson(@Valid @RequestBody User user, @PathVariable Long userId) {
		Map<String, Object> map = new HashMap<>();

		if (userService.existsById(userId)) {
			User existing = userService.getUserById(userId).get();
			existing.setAddresses(user.getAddresses());
			existing.setEmail(user.getEmail());
			existing.setUsername(user.getUsername());
			existing.setPassword(user.getPassword());
			userService.addUser(existing);
			map.put("message", "success");
			map.put("data", existing);
		} else {
			throw new NoDataFoundException("User not found");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable long userId) {
		if (userService.existsById(userId)) {
			userService.deleteUserById(userId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			throw new NoDataFoundException("User not found, unable to delete");
		}
	}
}
