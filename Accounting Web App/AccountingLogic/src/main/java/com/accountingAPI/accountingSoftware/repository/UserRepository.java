package com.accountingAPI.accountingSoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.Passwords;
import com.accountingAPI.accountingSoftware.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.userNameID = :userID")
    Optional<User> findByUserID(@Param("userID") String userID);
    

    @Query("SELECT p FROM Passwords p WHERE p.passwordRef = :userRef")
    Optional<Passwords> findPasswordByUserId(@Param("userRef") Integer userRef);


}
