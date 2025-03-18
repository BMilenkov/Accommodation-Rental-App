package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.Accommodation;
import mk.ukim.finki.emt.model.dto.AccommodationDto;
import mk.ukim.finki.emt.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> save(AccommodationDto accommodation);

    Optional<Accommodation> update(Long id, AccommodationDto accommodation);

    void deleteById(Long id);

    Optional<Accommodation> markAsRented(Long id);

    List<Accommodation> findByFilters(String name, Category category, Long hostId);
}
