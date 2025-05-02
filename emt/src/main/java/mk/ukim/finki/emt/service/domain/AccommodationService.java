package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.views.AccommodationsByHostView;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    List<Accommodation> findAll(Specification<Accommodation> filter);

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> save(Accommodation accommodation);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    void deleteById(Long id);

    Optional<Accommodation> markAsRented(Long id);

    void refreshMaterializedView();

    List<AccommodationsByHostView> findAllAccommodationsByHostStatistics();
}
