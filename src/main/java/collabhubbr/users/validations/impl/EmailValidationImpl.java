package collabhubbr.users.validations.impl;

import collabhubbr.users.exceptions.EmailDuplicateException;
import collabhubbr.users.repository.UserRepository;
import collabhubbr.users.validations.EmailValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class EmailValidationImpl implements EmailValidation {

    private final UserRepository userRepository;

    @Override
    public void validate(String email) {
        userRepository.findByEmail(email)
                .ifPresent(existingUser -> {
                    log.error("User with email [{}] already exists", email);
                    throw new EmailDuplicateException("User with email " + email + " already exists");
                });
    }

}
