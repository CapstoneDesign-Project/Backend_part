package com.example.una.record.controller;

import com.example.una.record.Entity.RecordEntity;
import com.example.una.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/audio")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("/upload")
    public ResponseEntity<RecordEntity> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            RecordEntity savedFile = recordService.saveFile(file);
            return ResponseEntity.ok(savedFile);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
