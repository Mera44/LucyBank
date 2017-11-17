package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Statement;

@Repository
public interface StatementRepository extends CrudRepository<Statement, Long>{

}
