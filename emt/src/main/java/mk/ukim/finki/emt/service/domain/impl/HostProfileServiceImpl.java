package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.events.HostProfileEvent;
import mk.ukim.finki.emt.model.domain.HostProfile;
import mk.ukim.finki.emt.model.enumerations.HostProfileChangeType;
import mk.ukim.finki.emt.model.enumerations.Role;
import mk.ukim.finki.emt.model.exceptions.HostProfileNotFoundException;
import mk.ukim.finki.emt.model.projections.HostProfileProjection;
import mk.ukim.finki.emt.model.views.HostsPerCountryView;
import mk.ukim.finki.emt.repository.HostProfileRepository;
import mk.ukim.finki.emt.repository.HostsByCountryViewRepository;
import mk.ukim.finki.emt.service.domain.CountryService;
import mk.ukim.finki.emt.service.domain.HostProfileService;
import mk.ukim.finki.emt.service.domain.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostProfileServiceImpl implements HostProfileService {

    private final HostProfileRepository hostProfileRepository;
    private final HostsByCountryViewRepository hostsByCountryViewRepository;
    private final CountryService countryService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostProfileServiceImpl(HostProfileRepository hostProfileRepository, HostsByCountryViewRepository hostsByCountryViewRepository, CountryService countryService, UserService userService, ApplicationEventPublisher applicationEventPublisher) {
        this.hostProfileRepository = hostProfileRepository;
        this.hostsByCountryViewRepository = hostsByCountryViewRepository;
        this.countryService = countryService;
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<HostProfile> findAll() {
        return this.hostProfileRepository.findAll();
    }

    @Override
    public Optional<HostProfile> findById(Long id) {
        return this.hostProfileRepository.findById(id);
    }

    @Override
    public Optional<HostProfile> save(HostProfile hostProfile) {
        //Changing user role?!
        hostProfile.getUser().setRole(Role.ROLE_HOST);
        this.applicationEventPublisher.publishEvent(new HostProfileEvent(hostProfile, HostProfileChangeType.CREATED));
        return Optional.of(this.hostProfileRepository.save(hostProfile));
    }

    @Override
    public Optional<HostProfile> update(Long id, HostProfile hostProfile) {
        return this.hostProfileRepository.findById(id).map(existingHost -> {
            if (hostProfile.getUser() != null) {
                existingHost.setUser(this.userService.findByUsername(hostProfile.getUser().getUsername()).orElse(null));
            }
            if (hostProfile.getCountry() != null) {
                existingHost.setCountry(this.countryService.findById(hostProfile.getCountry().getId()).orElse(null));
            }
            this.applicationEventPublisher.publishEvent(new HostProfileEvent(hostProfile, HostProfileChangeType.UPDATED));
            return this.hostProfileRepository.save(existingHost);
        });
    }

    @Override
    public void deleteById(Long id) {
        HostProfile hostProfile = this.hostProfileRepository.findById(id).orElseThrow(() -> new HostProfileNotFoundException(id));
        this.hostProfileRepository.deleteById(id);
        this.applicationEventPublisher.publishEvent(new HostProfileEvent(hostProfile, HostProfileChangeType.UPDATED));
    }

    @Override
    public List<HostsPerCountryView> findAllHostsPerCountryStatistics() {
        return this.hostsByCountryViewRepository.findAll();
    }

    @Override
    public List<HostProfileProjection> getAllHostNames() {
        return this.hostProfileRepository.findAllHostNames();
    }

    @Override
    public void refreshMaterializedView() {
        this.hostsByCountryViewRepository.refreshMaterializedView();
    }
}

