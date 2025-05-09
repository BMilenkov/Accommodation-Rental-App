package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.views.AccommodationsByHostView;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseAccommodationByHostViewDto(Long hostProfileId, Integer numAccommodations) {

    public static ResponseAccommodationByHostViewDto from(AccommodationsByHostView accommodationsByHostView) {
        return new ResponseAccommodationByHostViewDto(
                accommodationsByHostView.getHostProfileId(),
                accommodationsByHostView.getNumAccommodations()
        );
    }

    public static List<ResponseAccommodationByHostViewDto> from(List<AccommodationsByHostView> accommodationsByHostViews) {
        return accommodationsByHostViews.stream().map(ResponseAccommodationByHostViewDto::from).collect(Collectors.toList());
    }
}
