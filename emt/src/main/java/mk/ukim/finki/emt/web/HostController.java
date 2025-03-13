package mk.ukim.finki.emt.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.model.Host;
import mk.ukim.finki.emt.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Host", description = "Host API")
@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> findAll() {
        return this.hostService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> findById(@PathVariable Long id) {
        return this.hostService.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Host> save(@RequestBody Host host) {
        return this.hostService.save(host)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody Host host) {
        return this.hostService.update(id, host)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (this.hostService.findById(id).isPresent()) {
            this.hostService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
