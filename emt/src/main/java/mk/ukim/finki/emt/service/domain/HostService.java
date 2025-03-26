package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.Host;
import mk.ukim.finki.emt.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface HostService extends UserDetailsService {

    List<Host> findAll();

    Optional<Host> findByUsername(String username);

    Optional<Host> update(String id, Host host);

    void deleteById(String username);

    Optional<Host> register(String username, String password, String repeatPassword, String name, String surname, Long country, Role role);

    Optional<Host> login(String username, String password);
}
