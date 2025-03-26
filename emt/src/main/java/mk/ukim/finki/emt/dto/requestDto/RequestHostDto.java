package mk.ukim.finki.emt.dto.requestDto;

import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.model.domain.Host;
import mk.ukim.finki.emt.model.enumerations.Role;

public record RequestHostDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Long country,
        Role role
) {
    public Host toHost(Country country) {
        return new Host(username, password, name, surname, country, role);
    }
}
