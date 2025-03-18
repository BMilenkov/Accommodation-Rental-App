package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.Accommodation;
import mk.ukim.finki.emt.model.dto.AccommodationDto;
import mk.ukim.finki.emt.model.enumerations.Category;
import mk.ukim.finki.emt.repository.AccommodationRepository;
import mk.ukim.finki.emt.service.AccommodationService;
import mk.ukim.finki.emt.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
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
    public Optional<Accommodation> save(AccommodationDto accommodation) {
        if (accommodation.getCategory() != null &&
                accommodation.getHost() != null && this.hostService.findById(accommodation.getHost()).isPresent()) {
            return Optional.of(
                    this.accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            this.hostService.findById(accommodation.getHost()).get(), accommodation.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodation) {
        return this.accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null && !accommodation.getName().isEmpty()) {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getHost() != null && this.hostService.findById(accommodation.getHost()).isPresent()) {
                        existingAccommodation.setHost(this.hostService.findById(accommodation.getHost()).get());
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
    public List<Accommodation> findByFilters(String name, Category category, Long hostId) {
        if (name != null && category != null && hostId != null) {
            return accommodationRepository.findAllByNameContainingIgnoreCaseAndCategoryAndHost_Id(name, category, hostId);
        } else if (name != null && category != null) {
            return accommodationRepository.findAllByNameContainingIgnoreCaseAndCategory(name, category);
        } else if (name != null && hostId != null) {
            return accommodationRepository.findAllByNameContainingIgnoreCaseAndHost_Id(name, hostId);
        } else if (category != null && hostId != null) {
            return accommodationRepository.findAllByCategoryAndHost_Id(category, hostId);
        } else if (name != null) {
            return accommodationRepository.findAllByNameContainingIgnoreCase(name);
        } else if (category != null) {
            return accommodationRepository.findAllByCategory(category);
        } else if (hostId != null) {
            return accommodationRepository.findAllByHost_Id(hostId);
        }
        return findAll();
    }
}
