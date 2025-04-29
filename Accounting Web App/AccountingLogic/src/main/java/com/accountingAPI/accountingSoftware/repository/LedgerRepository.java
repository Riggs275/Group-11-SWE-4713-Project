package com.accountingAPI.accountingSoftware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.LedgerEntry;

@Repository
public interface LedgerRepository extends JpaRepository<LedgerEntry, Integer> {
    List<LedgerEntry> findByAccountName(String accountName);

    // Get debits or credits per category for ratio calculations
    @Query("SELECT SUM(l.debit) FROM LedgerEntry l WHERE l.accountCategory = :accountCategory")
    double sumDebitsByAccountCategory(@Param("accountCategory") String category);
}
