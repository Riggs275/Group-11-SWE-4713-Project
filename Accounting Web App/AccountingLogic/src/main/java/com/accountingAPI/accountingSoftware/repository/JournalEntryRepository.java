package com.accountingAPI.accountingSoftware.repository;

import com.accountingAPI.accountingSoftware.components.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Integer> {
    List<JournalEntry> findByStatus(String status);
}
