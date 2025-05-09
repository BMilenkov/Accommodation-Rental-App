package mk.ukim.finki.emt.dto.requestDto;

import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.model.domain.HostProfile;
import mk.ukim.finki.emt.model.domain.User;

public record RequestHostProfileDto(
        Long country,
        String user
) {
    public HostProfile toHost(Country country, User user) {
        return new HostProfile(country, user);
    }
}
