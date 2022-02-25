package com.learning.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.dto.User;
import com.learning.exception.NoDataFoundException;
import com.learning.repo.UserRepo;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username).orElseThrow(
				()-> new NoDataFoundException("username not found"));
		return UserDetailsImpl.build(user);
	}

}
