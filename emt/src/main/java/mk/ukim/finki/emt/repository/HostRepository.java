package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, String> {

    Optional<Host> findByUsernameAndPassword(String username, String password);

    Optional<Host> findByUsername(String username);
}
