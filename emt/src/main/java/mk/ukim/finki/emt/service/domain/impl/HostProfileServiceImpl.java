package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.HostProfile;
import mk.ukim.finki.emt.model.enumerations.Role;
import mk.ukim.finki.emt.repository.HostProfileRepository;
import mk.ukim.finki.emt.service.domain.CountryService;
import mk.ukim.finki.emt.service.domain.HostProfileService;
import mk.ukim.finki.emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostProfileServiceImpl implements HostProfileService {

    private final HostProfileRepository hostProfileRepository;
    private final CountryService countryService;
    private final UserService userService;

    public HostProfileServiceImpl(HostProfileRepository hostProfileRepository, CountryService countryService, UserService userService) {
        this.hostProfileRepository = hostProfileRepository;
        this.countryService = countryService;
        this.userService = userService;
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
            return this.hostProfileRepository.save(existingHost);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.hostProfileRepository.deleteById(id);
    }
}

