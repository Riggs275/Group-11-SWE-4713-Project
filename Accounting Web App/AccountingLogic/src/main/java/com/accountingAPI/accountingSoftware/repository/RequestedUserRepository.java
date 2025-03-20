package com.accountingAPI.accountingSoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.RequestedUser;

@Repository
public interface RequestedUserRepository extends JpaRepository<RequestedUser, String> {


    @Query("SELECT r FROM RequestedUser r WHERE r.email = :email")
    Optional<RequestedUser> findByEmail(@Param("email") String email);

}
