package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.domain.Host;
import mk.ukim.finki.emt.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseAccommodationDto(
        Long id,
        String name,
        Category category,
        String host,
        Integer numRooms
) {
    public static ResponseAccommodationDto from(Accommodation accommodation) {
        return new ResponseAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getUsername(),
                accommodation.getNumRooms()
        );
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }

    public static List<ResponseAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(ResponseAccommodationDto::from).collect(Collectors.toList());
    }
}
