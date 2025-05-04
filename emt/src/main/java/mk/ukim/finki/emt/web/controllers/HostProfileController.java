package mk.ukim.finki.emt.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.dto.responseDto.ResponseHostProfileDto;
import mk.ukim.finki.emt.dto.requestDto.RequestHostProfileDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseHostsPerCountryViewDto;
import mk.ukim.finki.emt.model.projections.HostProfileProjection;
import mk.ukim.finki.emt.service.application.HostProfileApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostProfiles")
@Tag(name = "HostProfile API", description = "Endpoints for managing hostProfiles")
public class HostProfileController {

    private final HostProfileApplicationService hostService;

    public HostProfileController(HostProfileApplicationService hostService) {
        this.hostService = hostService;
    }

    @Operation(summary = "Get all hostProfiles", description = "Retrieves a list of all created HostProfiles")
    @GetMapping
    public List<ResponseHostProfileDto> findAll() {
        return this.hostService.findAll();
    }

    @Operation(summary = "Get hostProfile by ID", description = "Finds a hostProfile by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseHostProfileDto> findById(@PathVariable Long id) {
        return this.hostService.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new hostProfile", description = "Creates a new hostProfile.")
    @PostMapping("/add")
    public ResponseEntity<ResponseHostProfileDto> save(@RequestBody RequestHostProfileDto hostProfileDto) {
        return this.hostService.save(hostProfileDto)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing hostProfile", description = "Updates a hostProfile by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseHostProfileDto> update(@PathVariable Long id, @RequestBody RequestHostProfileDto host) {
        return this.hostService.update(id, host)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a hostProfile", description = "Deletes a hostProfile by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (this.hostService.findById(id).isPresent()) {
            this.hostService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Number of hosts by Country", description = "Get number of hosts per Country.")
    @GetMapping("/by-country")
    public List<ResponseHostsPerCountryViewDto> getHostsByCountryStatistics() {
        return this.hostService.findAllHostsPerCountryStatistics();
    }

    @Operation(summary = "Get all hosts by name and surname", description = "Get all hosts by name and surname.")
    @GetMapping("/names")
    public List<HostProfileProjection> getHostNames() {
        return this.hostService.getAllHostNames();
    }
}
