package com.accountingAPI.accountingSoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.accountingAPI.accountingSoftware.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
    
    boolean existsByUsername(String username);

    @Query("SELECT p.passwordHash FROM Password p JOIN Users u ON u.passwordRef = p.passwordRef WHERE u.userNameID = :userId")
    String findPasswordByUserId(@Param("userId") String userId);


}
