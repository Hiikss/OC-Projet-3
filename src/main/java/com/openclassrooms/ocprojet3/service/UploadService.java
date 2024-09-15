package com.openclassrooms.ocprojet3.service;

import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;

public interface UploadService {

    Resource getImageByName(String imageName);
}
