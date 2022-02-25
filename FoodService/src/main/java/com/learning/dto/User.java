package com.learning.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@EqualsAndHashCode(exclude = {"addresses", "roles"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"addresses","roles"})
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "username")})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String username;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate doj;
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Address> addresses;
	
	@ManyToMany
	@JoinTable(name="user_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();

	public User(@NotBlank String name, @NotBlank @Email String email, @NotBlank String password, Set<Address> addresses,
			Set<Role> roles) {
		super();
		this.username = name;
		this.email = email;
		this.password = password;
		this.addresses = addresses;
		this.roles = roles;
		doj = LocalDate.now();
	}
	
}
