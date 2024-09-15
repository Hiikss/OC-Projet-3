package com.openclassrooms.ocprojet3.configuration;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

@Component
@Getter
public class AppProperties {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expiration}")
    private Duration expirationDelay;

    @Value("${spring.web.resources.static-locations}")
    private String filePath;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

        try {
            Files.createDirectories(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!");
        }
    }

}
