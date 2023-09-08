package com.Techforb.Techforb.config.amazon3.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface IAWSClientService {

    File convertMultiPartToFile(MultipartFile file) throws IOException;

    String generateFileName(MultipartFile file);

    URL getImage(String fileName);

    String uploadFile(MultipartFile multipartFile);

    String deleteFileFromS3Bucket(String fileUrl);

}