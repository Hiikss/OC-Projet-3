package com.openclassrooms.ocprojet3.domains.auth;

import com.openclassrooms.ocprojet3.application.security.JwtTokenProvider;
import com.openclassrooms.ocprojet3.domains.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public AuthResponseDto login(CredentialsDto credentials) {
        log.info("[Auht Service] Login user");

        User user = userRepository.findByEmail(credentials.email())
                .orElseThrow(() -> new AuthenticationException("Invalid credentials"));

        if (!passwordEncoder.matches(credentials.password(), user.getPassword())) {
            throw new AuthenticationException("Invalid credentials");
        }

        return new AuthResponseDto(jwtTokenProvider.generateToken(user));
    }

    @Override
    public AuthResponseDto register(UserRequestDto userRequestDto) {
        log.info("[Auht Service] Register user");

        if (userRepository.findByEmail(userRequestDto.email()).isPresent()) {
            throw new UserException(HttpStatus.CONFLICT, "User already exists");
        }

        User user = userMapper.toUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));

        userRepository.save(user);
        return new AuthResponseDto(jwtTokenProvider.generateToken(user));
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        log.info("[Auht Service] Get current user");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "User with email " + email + " not found"));

        return userMapper.toUserResponseDto(user);
    }

}
