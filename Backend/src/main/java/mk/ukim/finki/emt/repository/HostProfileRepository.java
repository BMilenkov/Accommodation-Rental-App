package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.HostProfile;
import mk.ukim.finki.emt.model.projections.HostProfileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostProfileRepository extends JpaRepository<HostProfile, Long> {
    @Query("SELECT u.name AS name, u.surname AS surname " +
            "FROM HostProfile h JOIN h.user u")
    List<HostProfileProjection> findAllHostNames();
}
