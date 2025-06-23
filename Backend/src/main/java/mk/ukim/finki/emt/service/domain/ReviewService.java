package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll();

    Optional<Review> findById(Long id);

    Optional<Review> save(Review review);

    Optional<Review> update(Long id, Review review);

    void deleteById(Long id);

    Double getAverageRatingForAccommodation(Long accommodationId);
}
