package mk.ukim.finki.emt.listeners;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.events.AccommodationEvent;
import mk.ukim.finki.emt.model.domain.AccommodationHistory;
import mk.ukim.finki.emt.repository.AccommodationHistoryRepository;
import mk.ukim.finki.emt.service.domain.AccommodationHistoryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AccommodationEventListener {

    private final AccommodationHistoryService accommodationHistoryService;

    @EventListener
    public void handleAccommodationEvent(AccommodationEvent event) {
        var accommodation = event.getAccommodation();
        var type = event.getChangeType();

        AccommodationHistory history = new AccommodationHistory();
        history.setAccommodationId(accommodation.getId());
        history.setName(accommodation.getName());
        history.setChangeType(type);
        history.setTimestamp(LocalDateTime.now());

        this.accommodationHistoryService.save(history);
    }
}