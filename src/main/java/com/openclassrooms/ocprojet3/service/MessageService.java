package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.MessageRequestDto;

public interface MessageService {

    void createMessage(MessageRequestDto message);

}
