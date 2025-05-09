package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.AccommodationHistory;
import mk.ukim.finki.emt.repository.AccommodationHistoryRepository;
import mk.ukim.finki.emt.service.domain.AccommodationHistoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccommodationHistoryServiceImpl implements AccommodationHistoryService {

    private final AccommodationHistoryRepository accommodationHistoryRepository;

    public AccommodationHistoryServiceImpl(AccommodationHistoryRepository accommodationHistoryRepository) {
        this.accommodationHistoryRepository = accommodationHistoryRepository;
    }

    @Override
    public Optional<AccommodationHistory> save(AccommodationHistory accommodationHistory) {
        return Optional.of(this.accommodationHistoryRepository.save(accommodationHistory));
    }
}
