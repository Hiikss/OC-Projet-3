package com.openclassrooms.ocprojet3.mapper;

import com.openclassrooms.ocprojet3.dto.MessageRequestDto;
import com.openclassrooms.ocprojet3.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "rental", ignore = true)
    Message toMessage(MessageRequestDto messageRequestDto);
}
