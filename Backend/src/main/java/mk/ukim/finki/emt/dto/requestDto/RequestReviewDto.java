package mk.ukim.finki.emt.dto.requestDto;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

public record RequestReviewDto(
        String comment,
        Integer rating,
        Long accommodation
) {
    public static RequestReviewDto from(Review review) {
        return new RequestReviewDto(
                review.getComment(),
                review.getRating(),
                review.getAccommodation().getId()
        );
    }

    public Review toReview(Accommodation accommodation) {
        return new Review(comment, rating, accommodation);
    }

    public static List<RequestReviewDto> from(List<Review> reviews) {
        return reviews.stream().map(RequestReviewDto::from).collect(Collectors.toList());
    }
}
