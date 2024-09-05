package com.aurionpro.main.service;

import com.aurionpro.main.entity.FileEntity;
import com.aurionpro.main.repository.FileRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private FileRepository fileRepository;

    public FileEntity uploadFile(MultipartFile file) throws IOException {
        
        Map<String, Object> options = ObjectUtils.emptyMap();
        if (!file.getContentType().startsWith("image")) {
            options = ObjectUtils.asMap("resource_type", "raw");
        }

        // Upload file to Cloudinary with appropriate options
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);

        // Create and save FileEntity
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setUrl(uploadResult.get("url").toString());
        fileEntity.setFileType(file.getContentType());
        return fileRepository.save(fileEntity);
    }
}
