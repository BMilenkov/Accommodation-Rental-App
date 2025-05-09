package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.HostProfile;
import mk.ukim.finki.emt.model.projections.HostProfileProjection;
import mk.ukim.finki.emt.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostProfileService {

    List<HostProfile> findAll();

    Optional<HostProfile> save(HostProfile hostProfile);

    Optional<HostProfile> findById(Long id);

    Optional<HostProfile> update(Long id, HostProfile hostProfile);

    void refreshMaterializedView();

    void deleteById(Long id);

    List<HostsPerCountryView> findAllHostsPerCountryStatistics();

    List<HostProfileProjection> getAllHostNames();
}
