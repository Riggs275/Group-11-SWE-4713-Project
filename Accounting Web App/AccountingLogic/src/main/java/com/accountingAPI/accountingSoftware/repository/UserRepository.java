package com.accountingAPI.accountingSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountingAPI.accountingSoftware.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
