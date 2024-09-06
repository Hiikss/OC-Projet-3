package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
}
