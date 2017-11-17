package com.lucy.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Statement;

@Repository
public interface StatementRepository extends CrudRepository<Statement, Long>{
	public List<Statement> findByAccountAccountNumber(Integer accNo);
	@Query("selcet s Statement s where :date BETWEEN s.startDate AND s.endDate")
	public Statement findByDate(@Param("date")Date date);
	public Statement findByMonthName(String monthName);
}
