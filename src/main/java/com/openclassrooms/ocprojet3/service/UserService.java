package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.UserResponseDto;

public interface UserService {

    UserResponseDto getUserById(Long id);
}
