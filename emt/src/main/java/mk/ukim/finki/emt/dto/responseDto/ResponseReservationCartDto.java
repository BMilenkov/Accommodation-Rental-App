package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.domain.ReservationCart;
import mk.ukim.finki.emt.model.enumerations.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseReservationCartDto(
        Long id,
        LocalDateTime dateCreated,
        ResponseUserDto user,
        List<ResponseAccommodationDto> accommodations,
        ReservationStatus status
) {

    public static ResponseReservationCartDto from(ReservationCart reservationCart) {
        return new ResponseReservationCartDto(
                reservationCart.getId(),
                reservationCart.getDateCreated(),
                ResponseUserDto.from(reservationCart.getUser()),
                ResponseAccommodationDto.from(reservationCart.getAccommodations()),
                reservationCart.getStatus()
        );
    }
}

