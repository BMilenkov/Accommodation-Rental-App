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
import mk.ukim.finki.emt.service.specifications.builder.AccommodationSpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Specification<Accommodation> specification = AccommodationSpecificationBuilder.build(searchRequest);
        return ResponseAccommodationDto.from(this.accommodationService.findAll(specification));
    }

    @Override
    public Page<ResponseAccommodationDto> findAll(SearchRequestAccommodationDto searchRequest, Pageable pageable) {
        Specification<Accommodation> specification = AccommodationSpecificationBuilder.build(searchRequest);
        return this.accommodationService.findAll(specification, pageable)
                .map(ResponseAccommodationDto::from);
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
