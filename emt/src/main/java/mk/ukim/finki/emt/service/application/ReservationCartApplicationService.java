package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.responseDto.ResponseAccommodationDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseReservationCartDto;

import java.util.List;
import java.util.Optional;

public interface ReservationCartApplicationService {

    List<ResponseAccommodationDto> listAllAccommodationsInReservationCart(Long cartId);

    Optional<ResponseReservationCartDto> getActiveReservationCart(String username);

    Optional<ResponseReservationCartDto> addAccommodationToReservationCart(String username, Long accommodationId);

    Optional<ResponseReservationCartDto> removeAccommodationFromReservationCart(String username, Long accommodationId);

    Optional<ResponseReservationCartDto> confirmReservation(String username);

    Optional<ResponseReservationCartDto> cancelReservation(String username);

}
