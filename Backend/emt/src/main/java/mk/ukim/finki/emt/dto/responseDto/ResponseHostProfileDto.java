package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.domain.HostProfile;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseHostProfileDto(Long id, Long country, String user) {

    public static ResponseHostProfileDto from(HostProfile hostProfile) {
        return new ResponseHostProfileDto(
                hostProfile.getId(),
                hostProfile.getCountry().getId(),
                hostProfile.getUser().getUsername()
        );
    }

    public static List<ResponseHostProfileDto> from(List<HostProfile> hostProfiles) {
        return hostProfiles.stream().map(ResponseHostProfileDto::from).collect(Collectors.toList());
    }
}

