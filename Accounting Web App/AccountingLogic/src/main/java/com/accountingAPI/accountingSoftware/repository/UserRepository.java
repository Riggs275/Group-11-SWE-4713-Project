package com.accountingAPI.accountingSoftware.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    @Query("SELECT u FROM Users u WHERE u.userNameID = :userID")
    Optional<Users> findByUserID(@Param("userID") String userID);
    
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Users u WHERE u.role = :role")
    List<Users> findByRole(@Param("role") String role);


}
