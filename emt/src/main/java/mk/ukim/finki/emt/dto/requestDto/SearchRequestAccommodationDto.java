package mk.ukim.finki.emt.dto.requestDto;

import mk.ukim.finki.emt.model.enumerations.Category;

public record SearchRequestAccommodationDto (String name, Category category, String host){
}
