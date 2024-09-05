package com.aurionpro.main.service;

import com.aurionpro.main.entity.Image;
import com.aurionpro.main.repository.ImageRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) throws IOException {
        // Upload file to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // Create and save Image entity
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setUrl(uploadResult.get("url").toString());
        return imageRepository.save(image);
    }
}
