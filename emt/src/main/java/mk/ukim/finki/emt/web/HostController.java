package mk.ukim.finki.emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt.dto.requestDto.LoginRequestHostDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseCountryDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseHostDto;
import mk.ukim.finki.emt.dto.requestDto.RequestHostDto;
import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.emt.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.emt.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt.service.application.CountryApplicationService;
import mk.ukim.finki.emt.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "Endpoints for managing hosts")
public class HostController {

    private final HostApplicationService hostService;
    private final CountryApplicationService countryService;

    public HostController(HostApplicationService hostService, CountryApplicationService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Operation(summary = "Get all hosts", description = "Retrieves a list of all registered hosts.")
    @GetMapping
    public List<ResponseHostDto> findAll() {
        return this.hostService.findAll();
    }

    @Operation(summary = "Get host by ID", description = "Finds a host by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseHostDto> findById(@PathVariable String id) {
        return this.hostService.findByUsername(id)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing host", description = "Updates a host by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseHostDto> update(@PathVariable String id, @RequestBody RequestHostDto host) {
        return this.hostService.update(id, host)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host", description = "Deletes a host by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        if (this.hostService.findByUsername(id).isPresent()) {
            this.hostService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Register a new host", description = "Creates a new user account.")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/add")
    public ResponseEntity<ResponseHostDto> save(@RequestBody RequestHostDto host) {
        try {
            return this.hostService.register(host)
                    .map(m -> ResponseEntity.ok().body(m))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session.")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<ResponseHostDto> login(HttpServletRequest request) {
        try {
            ResponseHostDto responseUserDto = this.hostService.login(
                    new LoginRequestHostDto(request.getParameter("username"), request.getParameter("password"))
            ).orElseThrow(InvalidUserCredentialsException::new);

            Country country = this.countryService.findById(responseUserDto.country()).map(ResponseCountryDto::toCountry).orElse(null);
            request.getSession().setAttribute("user", responseUserDto.toHost(country));
            return ResponseEntity.ok(responseUserDto);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "User logout", description = "Ends the user's session.")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
