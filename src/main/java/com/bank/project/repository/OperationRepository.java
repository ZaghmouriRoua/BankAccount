package com.bank.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.project.models.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>{
	
	@Query("Select op from Operation op where op.account.id in :accounts")
	List<Operation> findByAccountsId(@Param("accounts") List<Long> accounts);

}
