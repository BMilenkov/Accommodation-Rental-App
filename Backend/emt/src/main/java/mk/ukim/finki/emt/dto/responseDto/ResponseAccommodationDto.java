package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseAccommodationDto(
        Long id,
        String name,
        Category category,
        Long hostProfile,
        Integer numRooms,
        Boolean isRented
) {
    public static ResponseAccommodationDto from(Accommodation accommodation) {
        return new ResponseAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHostProfile().getId(),
                accommodation.getNumRooms(),
                accommodation.getIsRented()
        );
    }

    public static List<ResponseAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(ResponseAccommodationDto::from).collect(Collectors.toList());
    }
}
