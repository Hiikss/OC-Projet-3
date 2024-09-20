package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.UserResponseDto;
import com.openclassrooms.ocprojet3.model.User;

public interface UserService {

    User getUserById(Long id);

    UserResponseDto getUserResponseDtoById(Long id);

    User getUserByEmail(String email);
}
