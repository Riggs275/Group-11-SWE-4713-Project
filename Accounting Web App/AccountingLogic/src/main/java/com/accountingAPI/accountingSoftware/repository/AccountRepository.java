package com.accountingAPI.accountingSoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    
    @Query("SELECT a FROM Account a WHERE a.account_name = :userName")
    Optional<Account> findByUserName(@Param("userName") String userName);


    
}
