package collabhubbr.users.controller;

import collabhubbr.users.models.UserEntity;
import collabhubbr.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {
    private UserService userService;

}
