package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.learning.dto.Role;
import com.learning.dto.User;
import com.learning.enums.RoleName;
import com.learning.repo.RoleRepo;
import com.learning.service.RoleService;

public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepo repo;
	@Override
	public Role addRole(Role role) {
		// TODO Auto-generated method stub
		return repo.save(role);
	}

	@Override
	public Optional<Role> getRoleById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public String deleteRoleById(long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return "Delete successful";
		}
		return "Delete failed, Id not found";
	}

	@Override
	public Role updateRole(Role role) {
		Role fromRepo = repo.getById(role.getRoleId());
		fromRepo.setRoleName(role.getRoleName());
		return repo.save(fromRepo);
	}

	@Override
	public List<Role> getAllRoleAscOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllRoleDescOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Role> findRoleByRoleName(RoleName roleName) {
		// TODO Auto-generated method stub
		return repo.findByRoleName(roleName);
	}

}
