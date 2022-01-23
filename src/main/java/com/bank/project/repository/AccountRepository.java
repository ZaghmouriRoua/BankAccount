package com.bank.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.project.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
		public Optional<Account> findByClientIdAndAccountNumber(Long id,Long accountNumber);
		public List<Account> findByClientId(Long id);
}
