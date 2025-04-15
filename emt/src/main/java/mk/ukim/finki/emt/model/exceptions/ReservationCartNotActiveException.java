package mk.ukim.finki.emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationCartNotActiveException extends RuntimeException {
    public ReservationCartNotActiveException(String username) {
        super(String.format("Active Reservation cart for user %s does not exists", username));
    }
}
