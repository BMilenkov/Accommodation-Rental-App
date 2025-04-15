package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.requestDto.RequestReviewDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseReviewDto;
import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.service.application.ReviewApplicationService;
import mk.ukim.finki.emt.service.domain.AccommodationService;
import mk.ukim.finki.emt.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewApplicationServiceImpl implements ReviewApplicationService {
    private final ReviewService reviewService;
    private final AccommodationService accommodationService;

    public ReviewApplicationServiceImpl(ReviewService reviewService, AccommodationService accommodationService) {
        this.reviewService = reviewService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<ResponseReviewDto> findAll() {
        return ResponseReviewDto.from(this.reviewService.findAll());
    }

    @Override
    public Optional<ResponseReviewDto> findById(Long id) {
        return this.reviewService.findById(id).map(ResponseReviewDto::from);
    }

    @Override
    public Optional<ResponseReviewDto> save(RequestReviewDto review) {
        Optional<Accommodation> accommodation = this.accommodationService.findById(review.accommodation());
        return accommodation.flatMap(value -> this.reviewService.save(review.toReview(accommodation.get()))
                .map(ResponseReviewDto::from));
    }

    @Override
    public Optional<ResponseReviewDto> update(Long id, RequestReviewDto review) {
        Optional<Accommodation> accommodation = this.accommodationService.findById(review.accommodation());
        return accommodation.flatMap(value -> this.reviewService.update(id, review.toReview(accommodation.get()))
                .map(ResponseReviewDto::from));
    }

    @Override
    public void deleteById(Long id) {
        this.reviewService.deleteById(id);
    }

    @Override
    public Double findAverageRating(Long accommodationId) {
        return this.reviewService.getAverageRatingForAccommodation(accommodationId);
    }
}
