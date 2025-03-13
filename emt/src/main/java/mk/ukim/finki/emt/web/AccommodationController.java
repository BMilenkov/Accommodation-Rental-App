package mk.ukim.finki.emt.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.model.Accommodation;
import mk.ukim.finki.emt.model.dto.AccommodationDto;
import mk.ukim.finki.emt.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Accommodation", description = "Accommodation API")
@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accommodation> findAll() {
        return this.accommodationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id) {
        return this.accommodationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> save(@RequestBody AccommodationDto accommodation) {
        return this.accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> update(@PathVariable Long id, @RequestBody AccommodationDto accommodation) {
        return this.accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.accommodationService.findById(id).isPresent()) {
            this.accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<Accommodation> markAsRented(@PathVariable Long id) {
        return this.accommodationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
