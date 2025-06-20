package collabhubbr.users.validations.impl;

import collabhubbr.users.DTO.RequestLoginDTO;
import collabhubbr.users.models.UserEntity;
import collabhubbr.users.validations.PasswordValidations;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PasswordValidationsImpl implements PasswordValidations {

    private final PasswordEncoder passwordEncoder;

    @Override
    public void validate(RequestLoginDTO user, UserEntity userEntity) {
        if (!this.passwordEncoder.matches(user.password(), userEntity.getPassword())) {
            log.error("Invalid email or password for user: [{}]", user.email());
            throw new IllegalArgumentException("Email ou senha inválidos");
        }
    }

}
