package com.learning.payload.response;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.payload.request.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {
	private long id;
	private String name;
	@Email
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate doj;

	private Set<Address> addresses;

	private Set<String> roles = new HashSet<>();

}
