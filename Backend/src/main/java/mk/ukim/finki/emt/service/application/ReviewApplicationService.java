package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.requestDto.RequestReviewDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewApplicationService {
    List<ResponseReviewDto> findAll();

    Optional<ResponseReviewDto> findById(Long id);

    Optional<ResponseReviewDto> save(RequestReviewDto review);

    Optional<ResponseReviewDto> update(Long id, RequestReviewDto review);

    void deleteById(Long id);
    Double findAverageRating(Long accommodationId);
}
