package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.requestDto.RequestCountryDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    List<ResponseCountryDto> findAll();

    Optional<ResponseCountryDto> findById(Long id);

    Optional<ResponseCountryDto> save(RequestCountryDto country);

    Optional<ResponseCountryDto> update(Long id, RequestCountryDto country);

    void deleteById(Long id);
}
