package com.accountingAPI.accountingSoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, java.lang.String> {
    
    
    @Query("SELECT a FROM Account a WHERE a.account_name = :accountName")
    Optional<Account> findByAccountName(@Param("accountName") String accountName);
    
    @Query("SELECT a FROM Account a WHERE a.account_number = :accountID")
    Optional<Account> findByAccountID(@Param("accountID") int accountID);

}
