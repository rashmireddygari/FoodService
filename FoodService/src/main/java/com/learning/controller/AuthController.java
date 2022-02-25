package com.learning.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.User;
import com.learning.payload.request.SignInRequest;
import com.learning.payload.request.SignUpRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.repo.RoleRepo;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.UserService;
import com.learning.utils.Builder;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	UserService userService;
	@Autowired
	RoleRepo roleRepo;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody SignInRequest signInRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
				signInRequest.getUsername(), signInRequest.getPassword())); 
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authentication.getPrincipal();
		List<String> roles = userDetailsImpl.getAuthorities()
				.stream()
				.map(e->e.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,userDetailsImpl.getId(),
				userDetailsImpl.getUsername(), 
				userDetailsImpl.getEmail(), 
				roles));
	}

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest request) {
		User user = Builder.buildUserFromSignUpRequest(request, roleRepo);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return ResponseEntity.status(201).body(userService.addUser(user));
	}

}
