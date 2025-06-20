package collabhubbr.users.validations.impl;

import collabhubbr.users.DTO.RequestLoginDTO;
import collabhubbr.users.models.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PasswordValidationsImplTest {

    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    PasswordValidationsImpl passwordValidations;

    @Test
    @DisplayName("Validate should throw IllegalArgumentException when password does not match")
    void case01() {
        RequestLoginDTO requestLoginDTO = new RequestLoginDTO(
                "john@email.com",
                "wrongPassword"
        );
        UserEntity userEntity = UserEntity.builder()
                .email("john@email.com")
                .password("password123")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            passwordValidations.validate(requestLoginDTO, userEntity);
        });

        verify(passwordEncoder, times(1))
                .matches(requestLoginDTO.password(), userEntity.getPassword());
    }

}