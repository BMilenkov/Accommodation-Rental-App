package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.Host;
import mk.ukim.finki.emt.model.enumerations.Role;
import mk.ukim.finki.emt.model.exceptions.*;
import mk.ukim.finki.emt.repository.HostRepository;
import mk.ukim.finki.emt.service.domain.CountryService;
import mk.ukim.finki.emt.service.domain.HostService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final PasswordEncoder passwordEncoder;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, PasswordEncoder passwordEncoder) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findByUsername(String username) {
        return this.hostRepository.findByUsername(username);
    }


    @Override
    public Optional<Host> update(String id, Host host) {
        return this.hostRepository.findById(id).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null) {
                existingHost.setCountry(this.countryService.findById(host.getCountry().getId()).orElse(null));
            }
            return this.hostRepository.save(existingHost);
        });
    }

    @Override
    public void deleteById(String username) {
        this.hostRepository.deleteById(username);
    }

    @Override
    public Optional<Host> register(
            String username,
            String password,
            String repeatPassword,
            String name,
            String surname,
            Long country,
            Role userRole
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword)) throw new PasswordsDoNotMatchException();
        if (this.hostRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        Host user = new Host(username, passwordEncoder.encode(password), name, surname,
                this.countryService.findById(country).orElse(null), userRole);
        return Optional.of(this.hostRepository.save(user));
    }

    @Override
    public Optional<Host> login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return Optional.of(this.hostRepository.findByUsernameAndPassword(username, password).orElseThrow(
                InvalidUserCredentialsException::new));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.hostRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }
}
