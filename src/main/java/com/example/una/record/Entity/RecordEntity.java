package com.example.una.record.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int recordId;
    private Date recordDate;
    private int recordSecond;
    private String recordUrl;
    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "teacher_id")
    private int teacherId;

    // Getters and setters
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRecordUrl() {
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl) {
        this.recordUrl = recordUrl;
    }
}
