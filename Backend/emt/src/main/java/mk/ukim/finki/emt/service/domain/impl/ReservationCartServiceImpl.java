package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.domain.ReservationCart;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.model.enumerations.ReservationStatus;
import mk.ukim.finki.emt.model.exceptions.*;
import mk.ukim.finki.emt.repository.ReservationCartRepository;
import mk.ukim.finki.emt.service.domain.AccommodationService;
import mk.ukim.finki.emt.service.domain.ReservationCartService;
import mk.ukim.finki.emt.service.domain.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationCartServiceImpl implements ReservationCartService {

    private final ReservationCartRepository reservationCartRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public ReservationCartServiceImpl(ReservationCartRepository reservationCartRepository, UserService userService, AccommodationService accommodationService) {
        this.reservationCartRepository = reservationCartRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Accommodation> listAllAccommodationsInReservationCart(Long cartId) {

        return reservationCartRepository.findById(cartId)
                .map(ReservationCart::getAccommodations)
                .orElseThrow(() -> new ReservationCartNotFoundException(cartId));
    }

    @Override
    public Optional<ReservationCart> getActiveReservationCart(String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return Optional.of(this.reservationCartRepository.findByUserAndStatus(
                user,
                ReservationStatus.CREATED
        ).orElseGet(() -> reservationCartRepository.save(new ReservationCart(user))));
    }

    @Override
    public Optional<ReservationCart> addAccommodationToReservationCart(String username, Long accommodationId) {
        Optional<ReservationCart> activeReservationCart = getActiveReservationCart(username);
        if (activeReservationCart.isPresent()) {
            ReservationCart reservationCart = activeReservationCart.get();
            Accommodation accommodation = this.accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
            System.out.println("Accommodation ID: " + accommodation.getId());

            boolean alreadyInCart = reservationCart.getAccommodations().stream()
                    .anyMatch(a -> a.getId().equals(accommodationId));
            if (alreadyInCart)
                throw new AccommodationAlreadyInReservationCartException(accommodationId, username);

            if (accommodation.getIsRented())
                throw new AccommodationNotAvailableException(accommodationId);

            reservationCart.getAccommodations().add(accommodation);
            return Optional.of(reservationCartRepository.save(reservationCart));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ReservationCart> removeAccommodationFromReservationCart(String username, Long accommodationId) {
        Optional<ReservationCart> activeReservationCart = getActiveReservationCart(username);
        if (activeReservationCart.isPresent()) {
            ReservationCart reservationCart = activeReservationCart.get();
            Accommodation accommodation = this.accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));

            boolean notInCart = reservationCart.getAccommodations().stream()
                    .noneMatch(a -> a.getId().equals(accommodationId));
            if (notInCart)
                throw new AccommodationNotFoundInReservationCartException(accommodationId,
                        reservationCart.getId());

            reservationCart.getAccommodations().remove(accommodation);
            return Optional.of(reservationCartRepository.save(reservationCart));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ReservationCart> confirmReservation(String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        ReservationCart reservationCart = this.reservationCartRepository.findByUserAndStatus(user, ReservationStatus.CREATED)
                .orElseThrow(() -> new ReservationCartNotActiveException(username));

        reservationCart.getAccommodations().forEach(accommodation ->
                this.accommodationService.markAsRented(accommodation.getId()));
        reservationCart.setStatus(ReservationStatus.FINISHED);

        return Optional.of(reservationCartRepository.save(reservationCart));
    }

    @Override
    public Optional<ReservationCart> cancelReservation(String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        ReservationCart reservationCart = this.reservationCartRepository.findByUserAndStatus(user, ReservationStatus.CREATED)
                .orElseThrow(() -> new ReservationCartNotActiveException(username));
        reservationCart.setStatus(ReservationStatus.CANCELED);

        return Optional.of(reservationCartRepository.save(reservationCart));
    }
}
