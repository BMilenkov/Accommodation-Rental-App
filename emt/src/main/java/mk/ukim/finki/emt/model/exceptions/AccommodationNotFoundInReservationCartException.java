package mk.ukim.finki.emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccommodationNotFoundInReservationCartException extends RuntimeException {
    public AccommodationNotFoundInReservationCartException(Long accommodationId, Long reservationCartId) {
        super(String.format("Accommodation with id: %d is not in Reservation cart with id: %d", accommodationId,
                reservationCartId));
    }
}
