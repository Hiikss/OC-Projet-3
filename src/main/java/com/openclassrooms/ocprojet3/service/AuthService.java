package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.AuthResponseDto;
import com.openclassrooms.ocprojet3.dto.CredentialsDto;
import com.openclassrooms.ocprojet3.dto.UserRequestDto;
import com.openclassrooms.ocprojet3.dto.UserResponseDto;

public interface AuthService {

    AuthResponseDto login(CredentialsDto credentials);

    AuthResponseDto register(UserRequestDto userRequestDto);

    UserResponseDto getUserByEmail(String email);
}
