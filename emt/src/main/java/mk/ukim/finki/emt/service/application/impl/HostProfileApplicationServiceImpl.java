package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.requestDto.RequestHostProfileDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseHostProfileDto;
import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.service.application.HostProfileApplicationService;
import mk.ukim.finki.emt.service.domain.CountryService;
import mk.ukim.finki.emt.service.domain.HostProfileService;
import mk.ukim.finki.emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostProfileApplicationServiceImpl implements HostProfileApplicationService {

    private final HostProfileService hostProfileService;
    private final CountryService countryService;
    private final UserService userService;

    public HostProfileApplicationServiceImpl(HostProfileService hostProfileService, CountryService countryService, UserService userService) {
        this.hostProfileService = hostProfileService;
        this.countryService = countryService;
        this.userService = userService;
    }

    @Override
    public List<ResponseHostProfileDto> findAll() {
        return ResponseHostProfileDto.from(this.hostProfileService.findAll());
    }

    @Override
    public Optional<ResponseHostProfileDto> findById(Long id) {
        return this.hostProfileService.findById(id).map(ResponseHostProfileDto::from);
    }

    @Override
    public Optional<ResponseHostProfileDto> save(RequestHostProfileDto host) {
        Optional<Country> country = this.countryService.findById(host.country());
        Optional<User> user = this.userService.findByUsername(host.user());
        return this.hostProfileService.save(host.toHost(country.orElse(null), user.orElse(null))).map(ResponseHostProfileDto::from);
    }

    @Override
    public Optional<ResponseHostProfileDto> update(Long id, RequestHostProfileDto host) {
        Optional<Country> country = this.countryService.findById(host.country());
        Optional<User> user = this.userService.findByUsername(host.user());
        return this.hostProfileService.update(id, host.toHost(country.orElse(null), user.orElse(null))).map(ResponseHostProfileDto::from);
    }

    @Override
    public void deleteById(Long id) {
        this.hostProfileService.deleteById(id);
    }
}
