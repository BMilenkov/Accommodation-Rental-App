package mk.ukim.finki.emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HostProfileNotFoundException extends RuntimeException {

    public HostProfileNotFoundException(Long id) {
        super(String.format("Host Profile with id: %d was not found", id));
    }
}
