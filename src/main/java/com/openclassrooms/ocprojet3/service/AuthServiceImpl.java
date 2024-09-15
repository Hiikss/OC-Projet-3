package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.configuration.AppProperties;
import com.openclassrooms.ocprojet3.dto.AuthResponseDto;
import com.openclassrooms.ocprojet3.dto.CredentialsDto;
import com.openclassrooms.ocprojet3.dto.UserRequestDto;
import com.openclassrooms.ocprojet3.dto.UserResponseDto;
import com.openclassrooms.ocprojet3.exception.UserException;
import com.openclassrooms.ocprojet3.mapper.UserMapper;
import com.openclassrooms.ocprojet3.model.User;
import com.openclassrooms.ocprojet3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final AppProperties appProperties;

    private final PasswordEncoder passwordEncoder;

    private final JwtEncoder jwtEncoder;

    @Override
    public AuthResponseDto login(CredentialsDto credentials) {
        log.info("[Auht Service] Login user");

        User user = userRepository.findByEmail(credentials.email())
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "User not found"));

        if (!passwordEncoder.matches(credentials.password(), user.getPassword())) {
            throw new UserException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }

        return new AuthResponseDto(generateToken(user));
    }

    @Override
    public AuthResponseDto register(UserRequestDto userRequestDto) {
        log.info("[Auht Service] Register user");

        if (userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new UserException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        User user = userMapper.toUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        userRepository.save(user);
        return new AuthResponseDto(generateToken(user));
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        log.info("[Auht Service] Get current user");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "User not found"));

        return userMapper.toUserResponseDto(user);
    }

    private String generateToken(User user) {
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
