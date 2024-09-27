package com.openclassrooms.ocprojet3.domains.message;

import com.openclassrooms.ocprojet3.domains.rental.Rental;
import com.openclassrooms.ocprojet3.domains.user.User;
import com.openclassrooms.ocprojet3.domains.rental.RentalService;
import com.openclassrooms.ocprojet3.domains.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    private final UserService userService;

    private final RentalService rentalService;

    @Override
    public void createMessage(MessageRequestDto messageRequestDto) {
        log.info("[Message Service] Creating new message");

        User user = userService.getUserById(messageRequestDto.userId());

        Rental rental = rentalService.getRentalById(messageRequestDto.rentalId());

        Message message = messageMapper.toMessage(messageRequestDto);
        message.setUser(user);
        message.setRental(rental);

        messageRepository.save(message);
    }
}
