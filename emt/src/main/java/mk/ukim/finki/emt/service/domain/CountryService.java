package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> save(Country category);

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, Country category);

    void deleteById(Long id);
}
