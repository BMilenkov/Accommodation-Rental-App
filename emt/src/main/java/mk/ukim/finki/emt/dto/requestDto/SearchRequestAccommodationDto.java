package mk.ukim.finki.emt.dto.requestDto;

import mk.ukim.finki.emt.model.enumerations.Category;

public record SearchRequestAccommodationDto(String name, Category category, Long hostProfile, Integer numRooms,
                                            Boolean isRented) {
}
