package mk.ukim.finki.emt.events;

import lombok.Getter;
import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.enumerations.AccommodationChangeType;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AccommodationEvent extends ApplicationEvent {

    private final Accommodation accommodation;
    private final LocalDateTime when;
    private final AccommodationChangeType changeType;

    public AccommodationEvent(Accommodation accommodation, AccommodationChangeType changeType) {
        super(accommodation);
        this.accommodation = accommodation;
        this.changeType = changeType;
        this.when = LocalDateTime.now();
    }
}

