package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.requestDto.RequestCountryDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseCountryDto;
import mk.ukim.finki.emt.service.application.CountryApplicationService;
import mk.ukim.finki.emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<ResponseCountryDto> findAll() {
        return ResponseCountryDto.from(this.countryService.findAll());
    }

    @Override
    public Optional<ResponseCountryDto> findById(Long id) {
        return this.countryService.findById(id).map(ResponseCountryDto::from);
    }

    @Override
    public Optional<ResponseCountryDto> save(RequestCountryDto country) {
        return this.countryService.save(country.toCountry()).map(ResponseCountryDto::from);
    }

    @Override
    public Optional<ResponseCountryDto> update(Long id, RequestCountryDto country) {
        return this.countryService.update(id, country.toCountry()).map(ResponseCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        this.countryService.deleteById(id);
    }
}
