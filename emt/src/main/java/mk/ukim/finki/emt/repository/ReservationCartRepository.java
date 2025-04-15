package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.ReservationCart;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.model.enumerations.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationCartRepository extends JpaRepository<ReservationCart, Long> {

    Optional<ReservationCart> findByUserAndStatus(User user, ReservationStatus status);
}
