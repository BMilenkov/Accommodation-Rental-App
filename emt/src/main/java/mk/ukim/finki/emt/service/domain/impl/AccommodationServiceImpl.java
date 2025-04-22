package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.events.AccommodationEvent;
import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.enumerations.AccommodationChangeType;
import mk.ukim.finki.emt.model.enumerations.Category;
import mk.ukim.finki.emt.model.exceptions.AccommodationNotFoundException;
import mk.ukim.finki.emt.repository.AccommodationRepository;
import mk.ukim.finki.emt.service.domain.AccommodationService;
import mk.ukim.finki.emt.service.domain.HostProfileService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostProfileService hostProfileService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostProfileService hostProfileService, ApplicationEventPublisher applicationEventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.hostProfileService = hostProfileService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Accommodation> findAll(Specification<Accommodation> filter) {
        return this.accommodationRepository.findAll(filter);
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        Optional<Accommodation> savedAccommodation = Optional.empty();
        if (accommodation.getCategory() != null &&
                accommodation.getHostProfile() != null && this.hostProfileService.findById(accommodation.getHostProfile().getId()).isPresent()) {

            savedAccommodation = Optional.of(this.accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                    accommodation.getHostProfile(), accommodation.getNumRooms())));

            this.applicationEventPublisher.publishEvent(new AccommodationEvent(savedAccommodation.get(), AccommodationChangeType.CREATED));
        }
        return savedAccommodation;
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return this.accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null && !accommodation.getName().isEmpty()) {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getHostProfile() != null && this.hostProfileService.findById(accommodation.getHostProfile().getId()).isPresent()) {
                        existingAccommodation.setHostProfile(accommodation.getHostProfile());
                    }
                    if (accommodation.getNumRooms() != null) {
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }

                    Accommodation saved = this.accommodationRepository.save(existingAccommodation);
                    this.applicationEventPublisher.publishEvent(new AccommodationEvent(saved, AccommodationChangeType.UPDATED));
                    return saved;
                });
    }

    @Override
    public void deleteById(Long id) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));
        this.accommodationRepository.deleteById(id);
        this.applicationEventPublisher.publishEvent(new AccommodationEvent(accommodation, AccommodationChangeType.DELETED));
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id).map(accommodation -> {
            accommodation.setNumRooms(0);
            accommodation.setIsRented(true);
            return accommodationRepository.save(accommodation);
        });
    }
}
