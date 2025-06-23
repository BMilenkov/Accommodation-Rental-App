package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.responseDto.ResponseAccommodationDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseReservationCartDto;
import mk.ukim.finki.emt.service.application.ReservationCartApplicationService;
import mk.ukim.finki.emt.service.domain.ReservationCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationCartApplicationServiceImpl implements ReservationCartApplicationService {

    private final ReservationCartService reservationCartService;

    public ReservationCartApplicationServiceImpl(ReservationCartService reservationCartService) {
        this.reservationCartService = reservationCartService;
    }

    @Override
    public List<ResponseAccommodationDto> listAllAccommodationsInReservationCart(Long cartId) {
        return ResponseAccommodationDto.from(this.reservationCartService.listAllAccommodationsInReservationCart(cartId));
    }

    @Override
    public Optional<ResponseReservationCartDto> getActiveReservationCart(String username) {
        return this.reservationCartService.getActiveReservationCart(username).map(ResponseReservationCartDto::from);
    }

    @Override
    public Optional<ResponseReservationCartDto> addAccommodationToReservationCart(String username, Long accommodationId) {
        return this.reservationCartService.addAccommodationToReservationCart(username, accommodationId)
                .map(ResponseReservationCartDto::from);
    }

    @Override
    public Optional<ResponseReservationCartDto> removeAccommodationFromReservationCart(String username, Long accommodationId) {
        return this.reservationCartService.removeAccommodationFromReservationCart(username, accommodationId)
                .map(ResponseReservationCartDto::from);
    }

    @Override
    public Optional<ResponseReservationCartDto> confirmReservation(String username) {
        return this.reservationCartService.confirmReservation(username).map(ResponseReservationCartDto::from);
    }

    @Override
    public Optional<ResponseReservationCartDto> cancelReservation(String username) {
        return this.reservationCartService.cancelReservation(username).map(ResponseReservationCartDto::from);
    }
}
