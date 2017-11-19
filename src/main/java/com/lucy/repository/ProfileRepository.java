package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile,Long>{
	
	

}
