package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.Host;
import mk.ukim.finki.emt.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> save(HostDto host);

    Optional<Host> update(Long id, HostDto host);

    void deleteById(Long id);
}
