package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.domain.HostProfile;
import mk.ukim.finki.emt.model.enumerations.Role;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseHostProfileDto(Long id, Long country, String username,
                                     String name, String surname, Role role
) {

    public static ResponseHostProfileDto from(HostProfile hostProfile) {
        return new ResponseHostProfileDto(
                hostProfile.getId(),
                hostProfile.getCountry().getId(),
                hostProfile.getUser().getUsername(),
                hostProfile.getUser().getName(),
                hostProfile.getUser().getSurname(),
                hostProfile.getUser().getRole()
        );
    }

    public static List<ResponseHostProfileDto> from(List<HostProfile> hostProfiles) {
        return hostProfiles.stream().map(ResponseHostProfileDto::from).collect(Collectors.toList());
    }
}

