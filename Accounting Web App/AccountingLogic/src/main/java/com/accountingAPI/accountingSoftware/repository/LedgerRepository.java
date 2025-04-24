package com.accountingAPI.accountingSoftware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.LedgerEntry;

@Repository
public interface LedgerRepository extends JpaRepository<LedgerEntry, Integer> {
    List<LedgerEntry> findByAccountName(String accountName);

    // Get debits or credits per category for ratio calculations
    @Query("SELECT SUM(e.debit) FROM LedgerEntry e WHERE e.accountCategory = :category")
    double sumByAccountCategory(@Param("category") String category);

    int countByStatus(String status);
}
