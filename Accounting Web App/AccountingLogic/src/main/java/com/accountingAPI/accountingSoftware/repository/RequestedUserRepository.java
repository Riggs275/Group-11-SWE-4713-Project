package com.accountingAPI.accountingSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.RequestedUser;

@Repository
public interface RequestedUserRepository extends JpaRepository<RequestedUser, String> {


    
}
