package com.example.una.record.service;

import com.example.una.record.Entity.RecordEntity;
import com.example.una.record.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class RecordService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private RecordRepository recordRepository;

    public RecordEntity saveFile(MultipartFile file) throws IOException {
        // Ensure the upload directory exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Save the file locally
        Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(filePath, file.getBytes());

        // Save the file path to the database
        RecordEntity audioFile = new RecordEntity();
        audioFile.setRecordUrl(filePath.toString());
        return recordRepository.save(audioFile);
    }
}
