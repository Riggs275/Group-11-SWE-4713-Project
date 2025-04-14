package com.accountingAPI.accountingSoftware.components;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "financial_statements")
public class FinancialStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statement_id")
    private int statementId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @Column(name = "generated_by", nullable = false)
    private String generatedBy;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;

    public FinancialStatement() {}

    // Getters and Setters
    public int getStatementId() {
        return statementId;
    }
    public void setStatementId(int statementIdSet) {
        statementIdSet = statementId;
    }
    public String getType() {
        return type;
    }
    public void setType(String typeSet) {
        typeSet = type;
    }
    public LocalDate getFromDate() {
        return fromDate;
    }
    public void setFromDate(LocalDate date) {
        date = fromDate;
    }
    public LocalDate getToDate() {
        return toDate;
    }
    public void setToDate(LocalDate date) {
        date = toDate;
    }
    public String getGeneratedBy() {
        return generatedBy;
    }
    public void setGeneratedBy(String userGenerated) {
        userGeneratedBy = generatedBy;
    }
    public byte[] getFileData() {
        return fileData;
    }
    public void setFileData(byte[] data) {
        data = fileData;
    }
}
