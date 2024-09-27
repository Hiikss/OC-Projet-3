package com.openclassrooms.ocprojet3.application.security;

import com.openclassrooms.ocprojet3.application.configuration.AppProperties;
import com.openclassrooms.ocprojet3.domains.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final AppProperties appProperties;

    private final JwtEncoder jwtEncoder;

    public String generateToken(User user) {
        log.info("[Auth Service] Generating JWT token");

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(appProperties.getExpirationDelay()))
                .subject(user.getEmail())
                .claim("scope", "ROLE_USER")
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }
}
