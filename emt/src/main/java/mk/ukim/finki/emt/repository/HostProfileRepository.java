package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.HostProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostProfileRepository extends JpaRepository<HostProfile, Long> {
}
