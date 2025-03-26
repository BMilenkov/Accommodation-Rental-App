package mk.ukim.finki.emt.dto.requestDto;

import mk.ukim.finki.emt.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record RequestCountryDto(String name, String continent) {

    public static RequestCountryDto from(Country country) {
        return new RequestCountryDto(country.getName(), country.getContinent());
    }

    public Country toCountry() {
        return new Country(name, continent);
    }

    public static List<RequestCountryDto> from(List<Country> countries) {
        return countries.stream().map(RequestCountryDto::from).collect(Collectors.toList());
    }
}
