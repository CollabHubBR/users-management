package collabhubbr.users.controller.impl;

import collabhubbr.users.controller.DTO.RequestLoginDTO;
import collabhubbr.users.controller.DTO.RequestUserDTO;
import collabhubbr.users.controller.DTO.ResponseLoginDTO;
import collabhubbr.users.controller.UserController;
import collabhubbr.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public ResponseEntity<Void> register(RequestUserDTO user) {
        log.info("Received request at endpoint /api/auth/register");
        this.userService.createAccount(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<ResponseLoginDTO> login(RequestLoginDTO user) {
        log.info("Received request at endpoint /api/auth/login");
        return ResponseEntity.ok().body(userService.loginAccount(user));
    }

}
