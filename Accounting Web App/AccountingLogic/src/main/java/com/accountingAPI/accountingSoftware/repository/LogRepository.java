package com.accountingAPI.accountingSoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    Optional<Log> findByChangedBy(String userID);
}
