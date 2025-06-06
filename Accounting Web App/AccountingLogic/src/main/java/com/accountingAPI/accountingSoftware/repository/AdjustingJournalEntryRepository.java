package com.accountingAPI.accountingSoftware.repository;

import com.accountingAPI.accountingSoftware.model.AdjustingJournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdjustingJournalEntryRepository extends JpaRepository<AdjustingJournalEntry, Integer> {

    List<AdjustingJournalEntry> findByStatus(String status);

    List<AdjustingJournalEntry> findBySubmittedBy(String submittedBy);

    // Find entries within a date range.
    @Query("SELECT a FROM AdjustingJournalEntry a WHERE a.date BETWEEN :startDate AND :endDate")
    List<AdjustingJournalEntry> findByDateRange(@Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
}
