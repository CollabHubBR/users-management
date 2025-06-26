package collabhubbr.users.service.impl;

import collabhubbr.users.models.UserEntity;
import collabhubbr.users.repository.UserRepository;
import collabhubbr.users.validations.EmailValidation;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersistenceServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    EmailValidation emailValidation;
    @InjectMocks
    PersistenceServiceImpl persistenceService;

    @Test
    @DisplayName("Should save user successfully")
    void case01() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("John Doe")
                .email("john@example.com")
                .password("123456789")
                .roles(RoleName.PUBLIC_USER)
                .build();

        var response = persistenceService.save(userEntity);

        assertAll("Response",
                () -> assertNotNull(response),
                () -> assertEquals(1, response.getId()),
                () -> assertEquals(userEntity.getEmail(), response.getEmail()),
                () -> assertEquals(userEntity.getUsername(), response.getUsername()),
                () -> assertEquals(userEntity.getPassword(), response.getPassword()),
                () -> assertEquals(userEntity.getRoles(), response.getRoles())
        );
        verify(userRepository, times(1))
                .save(any());
        verify(emailValidation, times(1))
                .validate(any());
    }

    @Test
    @DisplayName("Should find user by email")
    void case02() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("John Doe")
                .email("john@example.com")
                .password("123456789")
                .roles(RoleName.PUBLIC_USER)
                .build();

        when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.of(userEntity));

        var response = persistenceService.findByEmail(userEntity.getEmail());

        assertAll("Response",
                () -> assertNotNull(response),
                () -> assertEquals(1, response.getId()),
                () -> assertEquals(userEntity.getEmail(), response.getEmail()),
                () -> assertEquals(userEntity.getUsername(), response.getUsername()),
                () -> assertEquals(userEntity.getPassword(), response.getPassword()),
                () -> assertEquals(userEntity.getRoles(), response.getRoles())
        );
        verify(userRepository, times(1))
                .findByEmail(any());
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when user not found by email")
    void case03() {
        String email = "nonexistent@example.com";

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            persistenceService.findByEmail(email);
        });

        assertEquals("User with email " + email + " not found", exception.getMessage());
        verify(userRepository, times(1))
                .findByEmail(any());
    }

}