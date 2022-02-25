package com.learning.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.learning.dto.Address;
import com.learning.dto.Role;
import com.learning.dto.User;
import com.learning.enums.RoleName;
import com.learning.exception.IdNotFoundException;
import com.learning.payload.request.SignUpRequest;
import com.learning.payload.response.UserResponse;
import com.learning.repo.RoleRepo;

public class Builder {
//	@Autowired
//	static RoleRepo roleRepo;
//	{
//		
//	}

	public static UserResponse buildUserResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setEmail(user.getEmail());
		userResponse.setId(user.getId());
		userResponse.setName(user.getUsername());
		userResponse.setDoj(user.getDoj());
		Set<String> roles = new HashSet<>();
		user.getRoles().forEach(f->{
			roles.add(f.getRoleName().toString());
		});
		userResponse.setRoles(roles);
		Set<com.learning.payload.request.Address> addresses = new HashSet<>();
		user.getAddresses().forEach(f->{
			com.learning.payload.request.Address addressTemp = new com.learning.payload.request.Address();
			addressTemp.setCity(f.getCity());
			addressTemp.setHouseno(f.getHouseno());
			addressTemp.setCountry(f.getCountry());
			addressTemp.setState(f.getState());
			addressTemp.setStreet(f.getStreet());
			addressTemp.setZip((int) f.getZip());
			addresses.add(addressTemp);
		});
		userResponse.setAddresses(addresses);
		return userResponse;
	}
	
	public static User buildUserFromSignUpRequest(SignUpRequest request, RoleRepo roleRepo){
		Set<Role> roles = new HashSet<>();

		if (request.getRoles() == null || request.getRoles().isEmpty()) {
			Role role = roleRepo.findByRoleName(RoleName.ROLE_USER)
					.orElseThrow(() -> new IdNotFoundException("Role Not Found Exception"));
			roles.add(role);
		} else {
			request.getRoles().forEach(e -> {
				Role role = null;
				switch (e) {
				case "admin":
					role = roleRepo.findByRoleName(RoleName.ROLE_ADMIN)
							.orElseThrow(() -> new IdNotFoundException("Role Not Found Exception"));
					roles.add(role);
					break;
				case "user":
					role = roleRepo.findByRoleName(RoleName.ROLE_USER)
							.orElseThrow(() -> new IdNotFoundException("Role Not Found Exception"));
					roles.add(role);
					break;
				default:
					break;
				}
			});
		}
		User user = new User();
		Set<Address> addresses = new HashSet<>();
		request.getAddresses().forEach(e -> {
			Address address = new Address();
			address.setCity(e.getCity());
			address.setStreet(e.getStreet());
			address.setCountry(e.getCountry());
			address.setHouseno(e.getHouseno());
			address.setState(e.getState());
			address.setZip(e.getZip());
			address.setUser(user);
			addresses.add(address);
		});
		user.setUsername(request.getName());
		user.setAddresses(addresses);
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRoles(roles);
		if (request.getDoj() != null) {
			user.setDoj(request.getDoj());
		}
		return user;
	}
}
