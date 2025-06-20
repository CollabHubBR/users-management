package collabhubbr.users.controller;

import collabhubbr.users.DTO.*;
import collabhubbr.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RequestUserDTO user) {
        log.info("Received request at endpoint /api/auth/register");
        this.userService.createAccount(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@Valid @RequestBody RequestLoginDTO user) {
        log.info("Received request at endpoint /api/auth/login");
        return ResponseEntity.ok().body(userService.loginAccount(user));
    }

}
