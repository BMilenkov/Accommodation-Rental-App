package mk.ukim.finki.emt.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.model.enumerations.Category;

@NoArgsConstructor
public class AccommodationDto {

    private String name;

    private Category category;

    private Long host;

    private Integer numRooms;

    public AccommodationDto(String name, Category category, Long host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Long getHost() {
        return host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }
}
