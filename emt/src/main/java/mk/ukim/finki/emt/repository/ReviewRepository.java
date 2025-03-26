package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByAccommodationId(Long id);
}
