package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.configuration.AppProperties;
import com.openclassrooms.ocprojet3.exception.UploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final AppProperties appProperties;

    @Override
    public Resource getImageByName(String imageName) {
        try {
            // Load the file as a resource
            Path filePath = Paths.get(appProperties.getFilePath() + imageName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new UploadException(HttpStatus.NOT_FOUND, "File not found : " + imageName);
            }
        } catch (MalformedURLException e) {
            throw new UploadException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
