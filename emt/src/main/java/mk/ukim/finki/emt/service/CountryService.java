package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(long id);

    Optional<Country> save(Country country);

    Optional<Country> update(Long id, Country country);

    void deleteById(long id);
}
