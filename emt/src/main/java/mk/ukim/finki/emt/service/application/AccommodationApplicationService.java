package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.requestDto.RequestAccommodationDto;
import mk.ukim.finki.emt.dto.requestDto.SearchRequestAccommodationDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseAccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {

    List<ResponseAccommodationDto> findAll();

    Optional<ResponseAccommodationDto> findById(Long id);

    Optional<ResponseAccommodationDto> save(RequestAccommodationDto accommodation);

    Optional<ResponseAccommodationDto> update(Long id, RequestAccommodationDto accommodation);

    void deleteById(Long id);

    Optional<ResponseAccommodationDto> markAsRented(Long id);

    List<ResponseAccommodationDto> findByFilters(SearchRequestAccommodationDto requestAccommodationDto);
}
