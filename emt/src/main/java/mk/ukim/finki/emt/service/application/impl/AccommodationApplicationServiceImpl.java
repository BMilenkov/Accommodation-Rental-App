package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.requestDto.RequestAccommodationDto;
import mk.ukim.finki.emt.dto.requestDto.SearchRequestAccommodationDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseAccommodationDto;
import mk.ukim.finki.emt.model.domain.Host;
import mk.ukim.finki.emt.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.service.domain.AccommodationService;
import mk.ukim.finki.emt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<ResponseAccommodationDto> findAll() {
        return ResponseAccommodationDto.from(this.accommodationService.findAll());
    }

    @Override
    public Optional<ResponseAccommodationDto> findById(Long id) {
        return this.accommodationService.findById(id).map(ResponseAccommodationDto::from);
    }

    @Override
    public Optional<ResponseAccommodationDto> save(RequestAccommodationDto accommodation) {
        Optional<Host> host = this.hostService.findByUsername(accommodation.host());
        return host.flatMap(value -> this.accommodationService.save(accommodation.toAccommodation(value))
                .map(ResponseAccommodationDto::from));
    }

    @Override
    public Optional<ResponseAccommodationDto> update(Long id, RequestAccommodationDto accommodation) {
        Optional<Host> host = this.hostService.findByUsername(accommodation.host());
        return host.flatMap(value -> this.accommodationService.update(id, accommodation.toAccommodation(value))
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
    public List<ResponseAccommodationDto> findByFilters(SearchRequestAccommodationDto requestAccommodationDto) {
        return ResponseAccommodationDto.from(this.accommodationService.findByFilters(
                requestAccommodationDto.name(),
                requestAccommodationDto.category(),
                requestAccommodationDto.host()
        ));
    }
}
