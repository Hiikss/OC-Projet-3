package com.openclassrooms.ocprojet3.mapper;

import com.openclassrooms.ocprojet3.dto.UserRequestDto;
import com.openclassrooms.ocprojet3.dto.UserResponseDto;
import com.openclassrooms.ocprojet3.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rentals", ignore = true)
    @Mapping(target = "messages", ignore = true)
    User toUser(UserRequestDto userRequestDto);

    UserResponseDto toUserResponseDto(User user);
}
