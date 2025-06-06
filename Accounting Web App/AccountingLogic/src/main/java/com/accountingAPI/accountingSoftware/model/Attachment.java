package com.accountingAPI.accountingSoftware.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private int attachmentId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;

    public Attachment() {}

    // Getters and Setters
    public int getAttachmentId() {
        return attachmentId;
    }
    public void setAttachmentId(int attachmentIdSet) {
        attachmentId = attachmentIdSet;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileNameSet) {
        fileName = fileNameSet;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileTypeSet) {
        fileType = fileTypeSet;
    }
    public byte[] getFileData() {
        return fileData;
    }
    public void setFileData(byte[] fileDataSet) {
        fileData = fileDataSet;
    }
}
