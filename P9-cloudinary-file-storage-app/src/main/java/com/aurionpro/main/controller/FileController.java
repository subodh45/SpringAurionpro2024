package com.aurionpro.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.main.entity.FileEntity;
import com.aurionpro.main.service.FileService;

import java.io.IOException;

@RestController
@RequestMapping("/api/files") // Renamed from '/api/images'
public class FileController { // Renamed from 'ImageController'

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileEntity> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileEntity fileEntity = fileService.uploadFile(file);
            return ResponseEntity.ok(fileEntity);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}

