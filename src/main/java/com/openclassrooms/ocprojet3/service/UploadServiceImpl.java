package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.configuration.AppProperties;
import com.openclassrooms.ocprojet3.exception.UploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadServiceImpl implements UploadService {

    private final AppProperties appProperties;

    @Override
    public String uploadFile(MultipartFile file) {
        log.info("[Upload Service] Uploading file {}", file.getOriginalFilename());

        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(appProperties.getUploadDirectoryPath() + fileName);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new UploadException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while uploading file");
        }
    }

    @Override
    public Resource getUploadByName(String fileName) {
        log.info("[Upload Service] Get file by name {}", fileName);

        try {
            Path filePath = Paths.get(appProperties.getUploadDirectoryPath() + fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new UploadException(HttpStatus.NOT_FOUND, "File not found : " + fileName);
            }
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
            throw new UploadException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
