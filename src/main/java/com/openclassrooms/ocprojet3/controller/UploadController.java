package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploads")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final UploadService uploadService;

    @GetMapping(value = "/{name}")
    public ResponseEntity<Resource> getImageByName(@PathVariable String name) {
        log.info("[Upload Controller] Get uploaded image by name {}", name);

        return ResponseEntity.ok().body(uploadService.getUploadByName(name));
    }
}
