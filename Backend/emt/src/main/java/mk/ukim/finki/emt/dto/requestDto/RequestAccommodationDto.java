package mk.ukim.finki.emt.dto.requestDto;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.domain.HostProfile;
import mk.ukim.finki.emt.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record RequestAccommodationDto(
        String name,
        Category category,
        Long hostProfile,
        Integer numRooms
) {

    public static RequestAccommodationDto from(Accommodation accommodation) {
        return new RequestAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHostProfile().getId(),
                accommodation.getNumRooms()
        );
    }

    public Accommodation toAccommodation(HostProfile hostProfile) {
        return new Accommodation(name, category, hostProfile, numRooms);
    }

    public static List<RequestAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(RequestAccommodationDto::from).collect(Collectors.toList());
    }
}
