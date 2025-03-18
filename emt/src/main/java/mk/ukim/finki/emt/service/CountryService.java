package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.Country;
import mk.ukim.finki.emt.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(long id);

    Optional<Country> save(CountryDto country);

    Optional<Country> update(Long id, CountryDto country);

    void deleteById(long id);
}
