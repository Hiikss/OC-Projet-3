package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.AuthResponseDto;
import com.openclassrooms.ocprojet3.dto.CredentialsDto;
import com.openclassrooms.ocprojet3.dto.UserRequestDto;
import com.openclassrooms.ocprojet3.exception.UserException;
import com.openclassrooms.ocprojet3.model.User;
import com.openclassrooms.ocprojet3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void login(CredentialsDto credentialsDto) {
        User user = userRepository.findByEmail(credentialsDto.email())
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "Bad credentials"));

        if(!passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            throw new UserException(HttpStatus.BAD_REQUEST, "Bad credentials");
        }

    }

    @Override
    public AuthResponseDto register(UserRequestDto userRequestDto) {
        return null;
    }
}
