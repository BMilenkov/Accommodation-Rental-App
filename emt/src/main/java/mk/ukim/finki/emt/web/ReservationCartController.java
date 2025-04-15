package mk.ukim.finki.emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt.dto.responseDto.ResponseReservationCartDto;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.service.application.ReservationCartApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation-cart")
@Tag(name = "Reservation Cart API", description = "Endpoints for managing the reservation cart")
public class ReservationCartController {

    private final ReservationCartApplicationService reservationCartApplicationService;

    public ReservationCartController(ReservationCartApplicationService reservationCartApplicationService) {
        this.reservationCartApplicationService = reservationCartApplicationService;
    }

    @Operation(
            summary = "Get active reservation cart",
            description = "Retrieves the active reservation cart for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Reservation cart retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "Reservation cart not found")}
    )
    @GetMapping
    public ResponseEntity<ResponseReservationCartDto> getActiveReservationCart(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return this.reservationCartApplicationService.getActiveReservationCart(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add accommodation to reservation cart",
            description = "Adds a accommodation to the reservation cart for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Accommodation added to reservation cart successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Accommodation not found")}
    )
    @PostMapping("/add-accommodation/{id}")
    public ResponseEntity<ResponseReservationCartDto> addAccommodationToReservationCart(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return this.reservationCartApplicationService
                    .addAccommodationToReservationCart(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Remove accommodation from reservation cart",
            description = "Removes an accommodation from the reservation cart for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Accommodation removed from reservation cart successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Accommodation not found")}
    )
    @PostMapping("/remove-accommodation/{id}")
    public ResponseEntity<ResponseReservationCartDto> removeAccommodationFromReservationCart(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return this.reservationCartApplicationService
                    .removeAccommodationFromReservationCart(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Confirm reservation",
            description = "Confirms the logged-in user reservation and marks the accommodations as rented"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reservation confirmed successfully"),
                    @ApiResponse(responseCode = "404", description = "Active reservation cart not found")
            }
    )
    @PostMapping("/confirm")
    public ResponseEntity<ResponseReservationCartDto> confirmReservation(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return reservationCartApplicationService
                .confirmReservation(user.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Cancel reservation",
            description = "Cancels the logged-in user reservation"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reservation canceled successfully"),
                    @ApiResponse(responseCode = "404", description = "Active reservation cart not found")
            }
    )
    @PostMapping("/cancel")
    public ResponseEntity<ResponseReservationCartDto> cancelReservation(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return reservationCartApplicationService
                .cancelReservation(user.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
