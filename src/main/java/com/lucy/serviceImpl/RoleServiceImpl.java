package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucy.domain.Role;
import com.lucy.repository.RoleRepository;
import com.lucy.service.RoleService;

public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findById(long id) {
		
		return roleRepository.findOne(id);
	}

	@Override
	public List<Role> getAll() {
		
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public Role update(Role role) {
		
		roleRepository.save(role);
		return role;
	}

	@Override
	public boolean delete(long id) {
		
		Role role = roleRepository.findOne(id);
		roleRepository.delete(role);
		return true;
	}

	@Override
	public Role save(Role role) {
		
		return roleRepository.save(role);
	}
	
	
	
	

}
