package com.openclassrooms.ocprojet3.domains.upload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadFile(MultipartFile file);

    Resource getUploadByName(String fileName);
}
