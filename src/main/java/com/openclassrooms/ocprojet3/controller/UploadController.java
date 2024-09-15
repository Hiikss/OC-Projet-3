package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/uploads")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @GetMapping(value = "/{name}")
    public ResponseEntity<Resource> getImageByName(@PathVariable String name) {
        return ResponseEntity.ok().body(uploadService.getImageByName(name));
    }
}
