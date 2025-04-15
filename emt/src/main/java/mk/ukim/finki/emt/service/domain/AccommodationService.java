package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> save(Accommodation accommodation);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    void deleteById(Long id);

    Optional<Accommodation> markAsRented(Long id);

    List<Accommodation> findByFilters(String name, Category category, Long hostProfileId);
}
