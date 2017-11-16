package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucy.domain.Profile;
import com.lucy.repository.ProfileRepository;
import com.lucy.service.ProfileService;


public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	ProfileRepository profileRepository;

	@Override
	public Profile findById(long id) {
		
		return profileRepository.findOne(id);
	}

	@Override
	public List<Profile> getAll() {
		
		return (List<Profile>) profileRepository.findAll();
	}

	@Override
	public Profile update(Profile profile) {
		
		profileRepository.save(profile);
		return profile;
	}

	@Override
	public boolean delete(long id) {
		
		Profile profile = profileRepository.findOne(id);
		profileRepository.delete(profile);
		return true;
	}

	@Override
	public Profile save(Profile profile) {
		
		return profileRepository.save(profile);
	}
	
	
	
	

}
