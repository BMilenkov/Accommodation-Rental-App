package mk.ukim.finki.emt.service.specifications.builder;

import mk.ukim.finki.emt.dto.requestDto.SearchRequestAccommodationDto;
import mk.ukim.finki.emt.model.domain.Accommodation;
import org.springframework.data.jpa.domain.Specification;

import static mk.ukim.finki.emt.service.specifications.FieldFilterSpecification.*;

public class AccommodationSpecificationBuilder {

    private AccommodationSpecificationBuilder() {
    }

    public static Specification<Accommodation> build(SearchRequestAccommodationDto searchRequest) {
        Specification<Accommodation> specification = Specification.where(null);

        if (searchRequest.name() != null && !searchRequest.name().isBlank()) {
            specification = specification.and(filterContainsText(Accommodation.class, "name", searchRequest.name()));
        }

        if (searchRequest.category() != null) {
            specification = specification.and(filterEquals(Accommodation.class, "category", searchRequest.category().name()));
        }

        if (searchRequest.hostProfile() != null) {
            specification = specification.and(filterEquals(Accommodation.class, "hostProfile.id", searchRequest.hostProfile()));
        }

        if (searchRequest.numRooms() != null) {
            specification = specification.and(filterEqualsV(Accommodation.class, "numRooms", searchRequest.numRooms()));
        }

        if (searchRequest.isRented() != null) {
            specification = specification.and(filterEqualsV(Accommodation.class, "isRented", searchRequest.isRented()));
        }

        return specification;
    }
}
