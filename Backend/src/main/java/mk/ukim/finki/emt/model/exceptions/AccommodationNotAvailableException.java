package mk.ukim.finki.emt.model.exceptions;

public class AccommodationNotAvailableException extends RuntimeException {
    public AccommodationNotAvailableException(Long id) {
        super(String.format("Accommodation with id: %d is not available", id));
    }
}
