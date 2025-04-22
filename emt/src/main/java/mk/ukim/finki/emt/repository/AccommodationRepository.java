package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaSpecificationRepository<Accommodation, Long> {

}
