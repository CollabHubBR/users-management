package collabhubbr.users.controller;

import collabhubbr.users.DTO.*;
import collabhubbr.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseNewUserDTO> register(@Valid @RequestBody RequestNewUserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAccount(user));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@Valid @RequestBody RequestLoginDTO user) {
        return ResponseEntity.ok().body(userService.loginAccount(user));
    }

}
