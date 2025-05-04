package mk.ukim.finki.emt.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.dto.requestDto.LoginUserDto;
import mk.ukim.finki.emt.dto.requestDto.RequestUserDto;
import mk.ukim.finki.emt.dto.responseDto.LoginResponseDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseUserDto;
import mk.ukim.finki.emt.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.emt.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.emt.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<ResponseUserDto> register(@RequestBody RequestUserDto requestUserDto) {
        try {
            return userApplicationService.register(requestUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    //    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @Operation(summary = "User login", description = "Authenticates a user and generates a JWT")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(InvalidUserCredentialsException::new);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }

//        try {
//            ResponseUserDto displayUserDto = userApplicationService.login(
//                    new LoginUserDto(request.getParameter("username"), request.getParameter("password"))
//            ).orElseThrow(InvalidUserCredentialsException::new);
//
//            request.getSession().setAttribute("user", displayUserDto.toUser());
//            return ResponseEntity.ok(displayUserDto);
//        } catch (InvalidUserCredentialsException e) {
//            return ResponseEntity.notFound().build();
//        }
    }

//    @Operation(summary = "User logout", description = "Ends the user's session")
//    @ApiResponse(responseCode = "200", description = "User logged out successfully")
//    @GetMapping("/logout")
//    public void logout(HttpServletRequest request) {
//        request.getSession().invalidate();
//    }
}

