package mk.ukim.finki.emt.dto.responseDto;


import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseReviewDto(
        Long id,
        String comment,
        Integer rating,
        Long accommodationId
) {
    public static ResponseReviewDto from(Review review) {
        return new ResponseReviewDto(
                review.getId(),
                review.getComment(),
                review.getRating(),
                review.getAccommodation().getId()
        );
    }

    public Review toReview(Accommodation accommodation) {
        return new Review(comment, rating, accommodation);
    }

    public static List<ResponseReviewDto> from(List<Review> reviews) {
        return reviews.stream().map(ResponseReviewDto::from).collect(Collectors.toList());
    }

}
