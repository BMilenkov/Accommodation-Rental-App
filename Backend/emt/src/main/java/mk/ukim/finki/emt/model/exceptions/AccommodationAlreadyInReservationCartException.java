package mk.ukim.finki.emt.model.exceptions;

public class AccommodationAlreadyInReservationCartException extends RuntimeException {
    public AccommodationAlreadyInReservationCartException(Long id, String username) {
        super(String.format("Accommodation with id: %d already exists in reservation cart for user with username %s", id, username));
    }
}
