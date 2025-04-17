package com.accountingAPI.accountingSoftware.repository;

import com.accountingAPI.accountingSoftware.components.ErrorMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorMessage, Integer> {
    List<ErrorMessage> findByEntryId(Integer entryId);
}
