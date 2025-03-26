package mk.ukim.finki.emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.dto.requestDto.RequestReviewDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseReviewDto;
import mk.ukim.finki.emt.service.application.ReviewApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Review API", description = "Endpoints for managing reviews")
public class ReviewController {

    private final ReviewApplicationService reviewApplicationService;

    public ReviewController(ReviewApplicationService reviewApplicationService) {
        this.reviewApplicationService = reviewApplicationService;
    }


    @Operation(summary = "Get all reviews", description = "Retrieves a list of all available reviews.")
    @GetMapping
    public List<ResponseReviewDto> findAll() {
        return reviewApplicationService.findAll();
    }


    @Operation(summary = "Get review by ID", description = "Finds an review by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseReviewDto> findById(@PathVariable Long id) {
        return this.reviewApplicationService
                .findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new review", description = "Creates a new review.")
    @PostMapping("/add")
    public ResponseEntity<ResponseReviewDto> save(@RequestBody RequestReviewDto review) {
        return this.reviewApplicationService.save(review)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing review", description = "Updates an review by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseReviewDto> update(@PathVariable Long id, @RequestBody RequestReviewDto review) {
        return this.reviewApplicationService.update(id, review)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an review", description = "Deletes an review by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.reviewApplicationService.findById(id).isPresent()) {
            this.reviewApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
