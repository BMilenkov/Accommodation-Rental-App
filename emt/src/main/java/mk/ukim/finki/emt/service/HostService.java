package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> save(Host host);

    Optional<Host> update(Long id, Host host);

    void deleteById(Long id);
}
