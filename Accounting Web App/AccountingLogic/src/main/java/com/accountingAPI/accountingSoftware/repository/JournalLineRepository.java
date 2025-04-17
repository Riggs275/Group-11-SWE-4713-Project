package com.accountingAPI.accountingSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.JournalLine;

@Repository
public interface JournalLineRepository extends JpaRepository<JournalLine, Integer> {
  
}