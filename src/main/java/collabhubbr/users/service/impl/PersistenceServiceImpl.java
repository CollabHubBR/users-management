package collabhubbr.users.service.impl;

import collabhubbr.users.exceptions.EmailDuplicateException;
import collabhubbr.users.models.UserEntity;
import collabhubbr.users.repository.UserRepository;
import collabhubbr.users.service.PersistenceService;
import collabhubbr.users.validations.EmailValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PersistenceServiceImpl implements PersistenceService {

    private final UserRepository userRepository;
    private final EmailValidation emailValidation;

    @Override
    public UserEntity save(UserEntity userEntity) {
        this.emailValidation.validate(userEntity.getEmail());
        log.debug("Saving user with email: [{}]", userEntity.getEmail());
        this.userRepository.save(userEntity);
        log.debug("User saved successfully: [{}]", userEntity.getEmail());
        return userEntity;
    }

    @Override
    public UserEntity findByEmail(String email) {
        log.debug("Searching for user with email: [{}]", email);
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User with email [{}] not found", email);
                    return new EntityNotFoundException("User with email " + email + " not found");
                });
    }

}
