package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.AuthResponseDto;
import com.openclassrooms.ocprojet3.dto.CredentialsDto;
import com.openclassrooms.ocprojet3.dto.UserRequestDto;

public interface AuthService {

    void login(CredentialsDto credentialsDto);

    AuthResponseDto register(UserRequestDto userRequestDto);
}
