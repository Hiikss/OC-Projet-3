package com.openclassrooms.ocprojet3.domains.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        log.info("[User Service] Get user by id: {}", id);

        return userRepository.findById(id)
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
    }

    @Override
    public UserResponseDto getUserResponseDtoById(Long id) {
        return userMapper.toUserResponseDto(getUserById(id));
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("[User Service] Get user by email: {}", email);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "User with email " + email + " not found"));
    }
}
