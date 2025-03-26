package mk.ukim.finki.emt.dto.requestDto;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.domain.Host;
import mk.ukim.finki.emt.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record RequestAccommodationDto(
        String name,
        Category category,
        String host,
        Integer numRooms
) {

    public static RequestAccommodationDto from(Accommodation accommodation) {
        return new RequestAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getUsername(),
                accommodation.getNumRooms()
        );
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }

    public static List<RequestAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(RequestAccommodationDto::from).collect(Collectors.toList());
    }
}
