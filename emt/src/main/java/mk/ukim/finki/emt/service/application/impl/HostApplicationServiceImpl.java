package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.requestDto.LoginRequestHostDto;
import mk.ukim.finki.emt.dto.requestDto.RequestHostDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseHostDto;
import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.service.application.HostApplicationService;
import mk.ukim.finki.emt.service.domain.CountryService;
import mk.ukim.finki.emt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<ResponseHostDto> findAll() {
        return ResponseHostDto.from(this.hostService.findAll());
    }

    @Override
    public Optional<ResponseHostDto> findByUsername(String username) {
        return this.hostService.findByUsername(username).map(ResponseHostDto::from);
    }

    @Override
    public Optional<ResponseHostDto> update(String id, RequestHostDto host) {
        Optional<Country> country = this.countryService.findById(host.country());
        return this.hostService.update(id, host.toHost(country.orElse(null))).map(ResponseHostDto::from);
    }

    @Override
    public void deleteById(String username) {
        this.hostService.deleteById(username);
    }

    @Override
    public Optional<ResponseHostDto> register(RequestHostDto host) {
        return this.hostService.register(
                host.username(),
                host.password(),
                host.repeatPassword(),
                host.name(),
                host.surname(),
                host.country(),
                host.role()
        ).map(ResponseHostDto::from);
    }

    @Override
    public Optional<ResponseHostDto> login(LoginRequestHostDto loginHostDto) {
        return this.hostService.login(loginHostDto.username(), loginHostDto.password()).map(ResponseHostDto::from);
    }
}
