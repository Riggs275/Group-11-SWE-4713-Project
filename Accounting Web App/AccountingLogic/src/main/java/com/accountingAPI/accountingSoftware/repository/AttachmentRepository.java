package com.accountingAPI.accountingSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accountingAPI.accountingSoftware.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> { }
