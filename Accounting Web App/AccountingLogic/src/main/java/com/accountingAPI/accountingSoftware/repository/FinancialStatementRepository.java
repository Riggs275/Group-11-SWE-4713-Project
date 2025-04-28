package com.accountingAPI.accountingSoftware.repository;

import com.accountingAPI.accountingSoftware.model.FinancialStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialStatementRepository extends JpaRepository<FinancialStatement, Integer> {
    List<FinancialStatement> findByFromDateGreaterThanEqualAndToDateLessThanEqual(LocalDate fromDate, LocalDate toDate);
}
