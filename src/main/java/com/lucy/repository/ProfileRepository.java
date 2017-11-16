package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import com.lucy.domain.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long>{
	

}
