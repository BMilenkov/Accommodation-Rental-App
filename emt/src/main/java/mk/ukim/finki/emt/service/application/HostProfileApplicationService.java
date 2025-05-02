package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.responseDto.ResponseHostProfileDto;
import mk.ukim.finki.emt.dto.requestDto.RequestHostProfileDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseHostsPerCountryViewDto;
import mk.ukim.finki.emt.model.projections.HostProfileProjection;

import java.util.List;
import java.util.Optional;

public interface HostProfileApplicationService {

    List<ResponseHostProfileDto> findAll();

    Optional<ResponseHostProfileDto> findById(Long id);

    Optional<ResponseHostProfileDto> save(RequestHostProfileDto requestHostProfileDto);

    Optional<ResponseHostProfileDto> update(Long id, RequestHostProfileDto host);

    void deleteById(Long id);

    List<ResponseHostsPerCountryViewDto> findAllHostsPerCountryStatistics();

    List<HostProfileProjection> getAllHostNames();
}
