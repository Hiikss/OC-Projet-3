package com.openclassrooms.ocprojet3.repository;

import com.openclassrooms.ocprojet3.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
