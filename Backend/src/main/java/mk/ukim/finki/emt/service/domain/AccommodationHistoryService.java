package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.AccommodationHistory;

import java.util.Optional;

public interface AccommodationHistoryService {
    Optional<AccommodationHistory> save(AccommodationHistory accommodationHistory);
}
