package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseCountryDto(Long id, String name, String continent) {

    public static ResponseCountryDto from(Country country) {
        return new ResponseCountryDto(country.getId(), country.getName(), country.getContinent());
    }

    public Country toCountry() {
        return new Country(continent, name);
    }

    public static List<ResponseCountryDto> from(List<Country> countries) {
        return countries.stream().map(ResponseCountryDto::from).collect(Collectors.toList());
    }
}
