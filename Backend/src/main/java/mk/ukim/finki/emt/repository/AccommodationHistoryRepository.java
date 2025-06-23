package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.AccommodationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationHistoryRepository extends JpaRepository<AccommodationHistory, Long> {
}