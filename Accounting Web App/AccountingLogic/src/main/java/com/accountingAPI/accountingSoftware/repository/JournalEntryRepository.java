package com.accountingAPI.accountingSoftware.repository;

import com.accountingAPI.accountingSoftware.model.JournalEntry;
import com.accountingAPI.accountingSoftware.model.JournalLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Integer> {
    List<JournalEntry> findByStatus(String status);

    int countByStatus(String string);

    @Query("""
      SELECT je
        FROM JournalEntry je
        JOIN je.lines jl
      WHERE jl.accountName = ?1
    """)
    List<JournalEntry> findByLineAccountName(String accountName);
}
