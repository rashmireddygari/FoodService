package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.dto.Role;
import com.learning.enums.RoleName;

public interface RoleService {
	public Role addRole(Role role);
	public Optional<Role> getRoleById(long id);
	public List<Role> getAllRoles();
	public String deleteRoleById(long id);
	public Role updateRole(Role role);
	public List<Role> getAllRoleAscOrder();
	public List<Role> getAllRoleDescOrder();
	public Optional<Role> findRoleByRoleName(RoleName roleName);
}
