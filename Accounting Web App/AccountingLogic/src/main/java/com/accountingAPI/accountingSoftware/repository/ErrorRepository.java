package com.accountingAPI.accountingSoftware.repository;

import com.accountingAPI.accountingSoftware.model.ErrorMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorMessage, Integer> {
    List<ErrorMessage> findByEntryId(Integer entryId);
}
