package com.openclassrooms.ocprojet3.domains.auth;

import com.openclassrooms.ocprojet3.domains.user.UserRequestDto;
import com.openclassrooms.ocprojet3.domains.user.UserResponseDto;

public interface AuthService {

    AuthResponseDto login(CredentialsDto credentials);

    AuthResponseDto register(UserRequestDto userRequestDto);

    UserResponseDto getUserByEmail(String email);
}
