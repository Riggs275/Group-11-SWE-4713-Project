package com.accountingAPI.accountingSoftware.repositories;

import java.util.Optional;

import com.accountingAPI.accountingSoftware.components.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    Optional<Log> findByChangedBy(String userID);
}
