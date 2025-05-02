package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.Accommodation;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaSpecificationRepository<Accommodation, Long> {

}
