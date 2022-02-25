package com.learning.payload.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.dto.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	@NotEmpty
	private Set<Address> addresses;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate doj;
	@NotEmpty
	private Set<String> roles;
	
}
