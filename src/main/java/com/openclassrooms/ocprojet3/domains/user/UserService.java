package com.openclassrooms.ocprojet3.domains.user;

public interface UserService {

    User getUserById(Long id);

    UserResponseDto getUserResponseDtoById(Long id);

    User getUserByEmail(String email);
}
