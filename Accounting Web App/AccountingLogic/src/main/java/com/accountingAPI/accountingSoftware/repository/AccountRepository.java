package com.accountingAPI.accountingSoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, java.lang.String> {
    
    
    Optional<Account> findByAccountName(String accountName);
    
    Optional<Account> findByAccountNumber(int accountNumber);

    @Query("SELECT a FROM Account a WHERE a.accountNumber = :acctNum")
    Optional<Account> findByAccountID(@Param("acctNum") int accountNumber);

}
