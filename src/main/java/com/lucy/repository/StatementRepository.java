package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;

import com.lucy.domain.Statement;

public interface StatementRepository extends CrudRepository<Statement, Long>{

}
