package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.model.domain.Host;
import mk.ukim.finki.emt.model.enumerations.Role;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseHostDto(String username, String name, String surname, Long country, Role role) {

    public static ResponseHostDto from(Host host) {
        return new ResponseHostDto(
                host.getUsername(),
                host.getName(),
                host.getSurname(),
                host.getCountry().getId(),
                host.getRole()
        );
    }

    public static List<ResponseHostDto> from(List<Host> hosts) {
        return hosts.stream().map(ResponseHostDto::from).collect(Collectors.toList());
    }

    public Host toHost(Country country) {
        return new Host(username, name, surname, country, role);
    }
}

