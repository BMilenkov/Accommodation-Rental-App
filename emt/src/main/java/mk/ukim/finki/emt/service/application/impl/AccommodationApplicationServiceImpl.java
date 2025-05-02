package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.requestDto.RequestAccommodationDto;
import mk.ukim.finki.emt.dto.requestDto.SearchRequestAccommodationDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseAccommodationByHostViewDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseAccommodationDto;
import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.domain.HostProfile;
import mk.ukim.finki.emt.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.service.domain.AccommodationService;
import mk.ukim.finki.emt.service.domain.HostProfileService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static mk.ukim.finki.emt.service.specifications.FieldFilterSpecification.*;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostProfileService hostProfileService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostProfileService hostProfileService) {
        this.accommodationService = accommodationService;
        this.hostProfileService = hostProfileService;
    }

    @Override
    public List<ResponseAccommodationDto> findAll(SearchRequestAccommodationDto searchRequest) {
        Specification<Accommodation> specification = Specification.where(null);

        if (searchRequest.name() != null && !searchRequest.name().isBlank()) {
            specification = specification.and(filterContainsText(Accommodation.class, "name", searchRequest.name()));
        }

        if (searchRequest.category() != null) {
            specification = specification.and(filterEquals(Accommodation.class, "category", searchRequest.category().name()));
        }

        if (searchRequest.hostProfile() != null) {
            specification = specification.and(filterEquals(Accommodation.class, "hostProfile.id", searchRequest.hostProfile()));
        }

        if (searchRequest.numRooms() != null) {
            specification = specification.and(filterEqualsV(Accommodation.class, "numRooms", searchRequest.numRooms()));
        }

        if (searchRequest.isRented() != null) {
            specification = specification.and(filterEqualsV(Accommodation.class, "isRented", searchRequest.isRented()));
        }
        return ResponseAccommodationDto.from(this.accommodationService.findAll(specification));
    }

    @Override
    public Optional<ResponseAccommodationDto> findById(Long id) {
        return this.accommodationService.findById(id).map(ResponseAccommodationDto::from);
    }

    @Override
    public Optional<ResponseAccommodationDto> save(RequestAccommodationDto accommodation) {
        Optional<HostProfile> hostProfile = this.hostProfileService.findById(accommodation.hostProfile());
        return hostProfile.flatMap(value -> this.accommodationService.save(accommodation.toAccommodation(value))
                .map(ResponseAccommodationDto::from));
    }

    @Override
    public Optional<ResponseAccommodationDto> update(Long id, RequestAccommodationDto accommodation) {
        Optional<HostProfile> hostProfile = this.hostProfileService.findById(accommodation.hostProfile());
        return hostProfile.flatMap(value -> this.accommodationService.update(id, accommodation.toAccommodation(value))
                .map(ResponseAccommodationDto::from));
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationService.deleteById(id);
    }

    @Override
    public Optional<ResponseAccommodationDto> markAsRented(Long id) {
        return this.accommodationService.markAsRented(id).map(ResponseAccommodationDto::from);
    }

    @Override
    public List<ResponseAccommodationByHostViewDto> findAllAccommodationsByHostStatistics() {
        return ResponseAccommodationByHostViewDto.from(this.accommodationService.findAllAccommodationsByHostStatistics());
    }
}
