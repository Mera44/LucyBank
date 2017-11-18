package com.lucy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Banker;

@Repository
public interface BankerRepository extends CrudRepository<Banker, Long>{
	@Query(value="SELECT b FROM Banker b WHERE b.profile.userName = :userName")
	public Banker findBankerByUsername(@Param("userName") String userName);
}
