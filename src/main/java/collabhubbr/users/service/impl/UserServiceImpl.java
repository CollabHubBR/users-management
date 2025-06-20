package collabhubbr.users.service.impl;

import collabhubbr.users.controller.DTO.RequestLoginDTO;
import collabhubbr.users.controller.DTO.RequestUserDTO;
import collabhubbr.users.controller.DTO.ResponseLoginDTO;
import collabhubbr.users.controller.DTO.ResponseNewUserDTO;
import collabhubbr.users.models.UserEntity;
import collabhubbr.users.service.PersistenceService;
import collabhubbr.users.service.UserService;
import collabhubbr.users.validations.PasswordValidations;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final TokenServiceImpl tokenServiceImpl;
    private final PersistenceService persistenceService;
    private final PasswordValidations passwordValidations;

    @Override
    public ResponseNewUserDTO createAccount(RequestUserDTO user) {
        UserEntity userEntity = UserEntity.builder()
                .username(user.username())
                .email(user.email())
                .password(passwordEncoder.encode(user.password()))
                .build();

        UserEntity response = this.persistenceService.save(userEntity);

        return new ResponseNewUserDTO(response);
    }

    @Override
    public ResponseLoginDTO loginAccount(RequestLoginDTO user) {
        UserEntity userEntity = this.persistenceService.findByEmail(user.email());

        this.passwordValidations.validate(user, userEntity);

        String token = this.tokenServiceImpl.generateToken(userEntity);

        return new ResponseLoginDTO(userEntity, token);
    }

}
