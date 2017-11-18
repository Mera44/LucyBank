package com.lucy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lucy.domain.Role;

@Service
public interface RoleService {
	
	public Role findById(long id);
	public List<Role> getAll();
	public Role update(Role role);
	public boolean delete(long id);
	public Role save(Role role);
	
}
