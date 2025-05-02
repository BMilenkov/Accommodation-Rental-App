package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.views.HostsPerCountryView;

import java.util.List;
import java.util.stream.Collectors;

public record ResponseHostsPerCountryViewDto(Long countryId, Integer numHosts) {

    public static ResponseHostsPerCountryViewDto from(HostsPerCountryView hostsByCountryView) {
        return new ResponseHostsPerCountryViewDto(
                hostsByCountryView.getCountryId(),
                hostsByCountryView.getNumHosts()
        );
    }

    public static List<ResponseHostsPerCountryViewDto> from(List<HostsPerCountryView> hostsByCountryViews) {
        return hostsByCountryViews.stream().map(ResponseHostsPerCountryViewDto::from).collect(Collectors.toList());
    }
}