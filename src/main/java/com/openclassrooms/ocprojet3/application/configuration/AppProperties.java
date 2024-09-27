package com.openclassrooms.ocprojet3.application.configuration;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

@Configuration
@Getter
public class AppProperties {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expiration}")
    private Duration expirationDelay;

    @Value("${upload-dir}")
    private String uploadDirectoryPath;

    @PostConstruct
    protected void init() throws ConfigurationException {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

        try {
            Files.createDirectories(Paths.get(uploadDirectoryPath));
        } catch (IOException e) {
            throw new ConfigurationException("Could not create upload directory");
        }
    }

}
