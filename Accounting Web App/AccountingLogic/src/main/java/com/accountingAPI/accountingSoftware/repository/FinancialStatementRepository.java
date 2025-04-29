package com.accountingAPI.accountingSoftware.repository;

import com.accountingAPI.accountingSoftware.model.FinancialStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialStatementRepository extends JpaRepository<FinancialStatement, Integer> {
    List<FinancialStatement> findByFromDateGreaterThanEqualAndToDateLessThanEqual(LocalDate fromDate, LocalDate toDate);

    @Query("SELECT f.type FROM FinancialStatement f WHERE f.id = :id")
    String getType(@Param("id") int id);

    @Query("SELECT f.fileData FROM FinancialStatement f WHERE f.id = :id")
    byte[] getFileData(@Param("id") int id);
}
