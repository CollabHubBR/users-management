package collabhubbr.users.service;

import collabhubbr.users.DTO.*;
import collabhubbr.users.models.UserEntity;
import collabhubbr.users.repository.UserRepository;
import collabhubbr.users.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseNewUserDTO createAccount(RequestNewUserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.username());
        userEntity.setEmail(user.email());
        userEntity.setPassword(passwordEncoder.encode(user.password()));
        userRepository.save(userEntity);
        return new ResponseNewUserDTO(user.username(), user.email());
    }

    public ResponseLoginDTO loginAccount(RequestLoginDTO user) {
        UserEntity userEntity = userRepository.findByEmail(user.email()).orElseThrow(
                () -> new IllegalArgumentException("Email invalido! ou não existe")
        );

        if (passwordEncoder.matches(user.password(), userEntity.getPassword())) {
            String token = tokenService.generateToken(userEntity);
            return new ResponseLoginDTO(userEntity.getId(), token, userEntity.getRoles());
        }

        throw new IllegalArgumentException("Senha invalida!");
    }
}
