package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.MessageRequestDto;
import com.openclassrooms.ocprojet3.exception.RentalException;
import com.openclassrooms.ocprojet3.exception.UserException;
import com.openclassrooms.ocprojet3.mapper.MessageMapper;
import com.openclassrooms.ocprojet3.model.Message;
import com.openclassrooms.ocprojet3.model.Rental;
import com.openclassrooms.ocprojet3.model.User;
import com.openclassrooms.ocprojet3.repository.MessageRepository;
import com.openclassrooms.ocprojet3.repository.RentalRepository;
import com.openclassrooms.ocprojet3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    private final UserRepository userRepository;

    private final RentalRepository rentalRepository;

    @Override
    public void createMessage(MessageRequestDto messageRequestDto) {
        log.info("[Message Service] Creating new message");

        User user = userRepository.findById(messageRequestDto.getUserId())
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "User with id " + messageRequestDto.getUserId() + " not found"));

        Rental rental = rentalRepository.findById(messageRequestDto.getRentalId())
                .orElseThrow(() -> new RentalException(HttpStatus.NOT_FOUND, "Rental with id " + messageRequestDto.getRentalId() + " not found"));

        Message message = messageMapper.toMessage(messageRequestDto);
        message.setUser(user);
        message.setRental(rental);

        messageRepository.save(message);
    }
}
