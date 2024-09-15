package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.UserResponseDto;
import com.openclassrooms.ocprojet3.exception.UserException;
import com.openclassrooms.ocprojet3.mapper.UserMapper;
import com.openclassrooms.ocprojet3.model.User;
import com.openclassrooms.ocprojet3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));

        return userMapper.toUserResponseDto(user);
    }
}
