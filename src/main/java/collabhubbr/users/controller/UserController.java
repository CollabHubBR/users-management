package collabhubbr.users.controller;

import collabhubbr.users.controller.DTO.RequestLoginDTO;
import collabhubbr.users.controller.DTO.RequestUserDTO;
import collabhubbr.users.controller.DTO.ResponseLoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "User Authentication", description = "Endpoints for user authentication")
@RequestMapping("/api/auth")
public interface UserController {

    @Operation(
            summary = "User Registration",
            description = "Register a new user"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user data",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already in use",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @PostMapping("/register")
    ResponseEntity<Void> register(@Valid @RequestBody RequestUserDTO user);

    @Operation(
            summary = "User Login",
            description = "Authenticate a user and return a JWT token"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User logged in successfully, returns JWT token"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user data",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid email or password",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @PostMapping("/login")
    ResponseEntity<ResponseLoginDTO> login(@Valid @RequestBody RequestLoginDTO user);

}
