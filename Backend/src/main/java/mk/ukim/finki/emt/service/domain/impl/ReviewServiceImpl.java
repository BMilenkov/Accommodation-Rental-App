package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.Review;
import mk.ukim.finki.emt.repository.ReviewRepository;
import mk.ukim.finki.emt.service.domain.AccommodationService;
import mk.ukim.finki.emt.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AccommodationService accommodationService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, AccommodationService accommodationService) {
        this.reviewRepository = reviewRepository;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return this.reviewRepository.findById(id);
    }

    @Override
    public Optional<Review> save(Review review) {
        return Optional.of(this.reviewRepository.save(review));
    }

    @Override
    public Optional<Review> update(Long id, Review review) {
        return this.reviewRepository.findById(id).map(existingReview -> {
            if (existingReview.getRating() != null) {
                existingReview.setRating(review.getRating());
            }
            if (existingReview.getComment() != null) {
                existingReview.setComment(review.getComment());
            }
            if (existingReview.getAccommodation() != null && this.accommodationService.findById(existingReview.getAccommodation().getId()).isPresent()) {
                existingReview.setAccommodation(review.getAccommodation());
            }
            return this.reviewRepository.save(existingReview);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.reviewRepository.deleteById(id);
    }

    @Override
    public Double getAverageRatingForAccommodation(Long id) {
        return this.reviewRepository.findAllByAccommodationId(id).stream().flatMapToInt(review ->
                IntStream.of(review.getRating())).average().orElse(0.0);
    }
}
