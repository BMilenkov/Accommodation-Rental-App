package mk.ukim.finki.emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.dto.requestDto.RequestAccommodationDto;
import mk.ukim.finki.emt.dto.requestDto.SearchRequestAccommodationDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseAccommodationDto;
import mk.ukim.finki.emt.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.service.application.ReviewApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationService;
    private final ReviewApplicationService reviewService;

    public AccommodationController(AccommodationApplicationService accommodationService, ReviewApplicationService reviewService) {
        this.accommodationService = accommodationService;
        this.reviewService = reviewService;
    }

    @Operation(summary = "Get all accommodations", description = "Retrieves a list of all available accommodations.")
    @GetMapping
    public List<ResponseAccommodationDto> findAll(@ModelAttribute SearchRequestAccommodationDto searchRequestAccommodationDto) {
        boolean hasFilters = searchRequestAccommodationDto.name() != null && !searchRequestAccommodationDto.name().isEmpty();

        if (searchRequestAccommodationDto.category() != null && !searchRequestAccommodationDto.category().name().isEmpty()) {
            hasFilters = true;
        }
        if (searchRequestAccommodationDto.host() != null && !searchRequestAccommodationDto.host().isEmpty()) {
            hasFilters = true;
        }

        if (hasFilters) {
            return this.accommodationService.findByFilters(searchRequestAccommodationDto);
        }
        return this.accommodationService.findAll();
    }


    @Operation(summary = "Get accommodation by ID", description = "Finds an accommodation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseAccommodationDto> findById(@PathVariable Long id) {
        return this.accommodationService
                .findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new accommodation", description = "Creates a new accommodation.")
    @PostMapping("/add")
    public ResponseEntity<ResponseAccommodationDto> save(@RequestBody RequestAccommodationDto accommodation) {
        return this.accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing accommodation", description = "Updates an accommodation by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseAccommodationDto> update(@PathVariable Long id, @RequestBody RequestAccommodationDto accommodation) {
        return this.accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an accommodation", description = "Deletes an accommodation by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.accommodationService.findById(id).isPresent()) {
            this.accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Mark accommodation as rented", description = "Marks an accommodation as rented by its ID.")
    @PutMapping("/rent/{id}")
    public ResponseEntity<ResponseAccommodationDto> markAsRented(@PathVariable Long id) {
        return this.accommodationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Average rating", description = "Get average review ratings by its ID.")
    @GetMapping("/average/{id}")
    public Double getAverageRating(@PathVariable Long id) {
        return this.reviewService.findAverageRating(id);
    }
}
