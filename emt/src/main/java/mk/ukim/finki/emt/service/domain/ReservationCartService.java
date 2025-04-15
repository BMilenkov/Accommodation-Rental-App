package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.dto.responseDto.ResponseReservationCartDto;
import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.domain.ReservationCart;

import java.util.List;
import java.util.Optional;

public interface ReservationCartService {

    List<Accommodation> listAllAccommodationsInReservationCart(Long cartId);

    Optional<ReservationCart> getActiveReservationCart(String username);

    Optional<ReservationCart> addAccommodationToReservationCart(String username, Long accommodationId);

    Optional<ReservationCart> removeAccommodationFromReservationCart(String username, Long accommodationId);

    Optional<ReservationCart> confirmReservation(String username);

    Optional<ReservationCart> cancelReservation(String username);

}
