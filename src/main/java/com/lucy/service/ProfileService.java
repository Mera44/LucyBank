package com.lucy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lucy.domain.Profile;

@Service
public interface ProfileService {
	
	public Profile findById(long id);
	public List<Profile> getAll();
	public Profile update(Profile profile);
	public boolean delete(long id);
	public Profile save(Profile profile);
	
}
