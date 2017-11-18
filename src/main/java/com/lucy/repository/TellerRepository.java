package com.lucy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Teller;

@Repository
public interface TellerRepository extends CrudRepository<Teller, Long>{
	@Query(value="SELECT t FROM Teller t WHERE t.profile.userName = :userName")
	public Teller findTellerByUsername(@Param("userName") String userName);
}
