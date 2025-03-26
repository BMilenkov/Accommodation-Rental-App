package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.enumerations.Category;
import mk.ukim.finki.emt.repository.AccommodationRepository;
import mk.ukim.finki.emt.repository.ReviewRepository;
import mk.ukim.finki.emt.service.application.HostApplicationService;
import mk.ukim.finki.emt.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostApplicationService hostService;
    private final ReviewRepository reviewRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostApplicationService hostService, ReviewRepository reviewRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;

        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getCategory() != null &&
                accommodation.getHost() != null && this.hostService.findByUsername(accommodation.getHost().getUsername()).isPresent()) {
            return Optional.of(
                    this.accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            accommodation.getHost(), accommodation.getNumRooms())));
        }
        return Optional.empty();
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
                    if (accommodation.getHost() != null && this.hostService.findByUsername(accommodation.getHost().getUsername()).isPresent()) {
                        existingAccommodation.setHost(accommodation.getHost());
                    }
                    if (accommodation.getNumRooms() != null) {
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    return this.accommodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id).map(accommodation -> {
            accommodation.setNumRooms(0);
            return accommodationRepository.save(accommodation);
        });
    }

    @Override
    public List<Accommodation> findByFilters(String name, Category category, String hostId) {

        boolean hasName = name != null && !name.isEmpty();
        boolean hasCategory = category != null;
        boolean hasHost = hostId != null && !hostId.isEmpty();

        if (hasName && hasCategory && hasHost) {
            return accommodationRepository.findAllByNameContainingIgnoreCaseAndCategoryAndHost_Username(name, category, hostId);
        } else if (hasName && hasCategory) {
            return accommodationRepository.findAllByNameContainingIgnoreCaseAndCategory(name, category);
        } else if (hasName && hasHost) {
            return accommodationRepository.findAllByNameContainingIgnoreCaseAndHost_Username(name, hostId);
        } else if (hasCategory && hasHost) {
            return accommodationRepository.findAllByCategoryAndHost_Username(category, hostId);
        } else if (hasName) {
            return accommodationRepository.findAllByNameContainingIgnoreCase(name);
        } else if (hasCategory) {
            return accommodationRepository.findAllByCategory(category);
        } else if (hasHost) {
            return accommodationRepository.findAllByHost_Username(hostId);
        }
        return findAll();
    }

    @Override
    public Double getAverageRating(Long id) {
        return this.reviewRepository.findAllByAccommodationId(id).stream().flatMapToInt(review ->
                IntStream.of(review.getRating())).average().orElse(0.0);
    }
}
