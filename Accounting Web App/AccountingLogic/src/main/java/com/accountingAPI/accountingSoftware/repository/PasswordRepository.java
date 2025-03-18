package com.accountingAPI.accountingSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.Passwords;

@Repository
public interface PasswordRepository extends JpaRepository<Passwords, Integer> {


    
}
