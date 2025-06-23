package collabhubbr.users.service.impl;

import collabhubbr.users.controller.DTO.RequestLoginDTO;
import collabhubbr.users.controller.DTO.RequestUserDTO;
import collabhubbr.users.controller.DTO.ResponseNewUserDTO;
import collabhubbr.users.models.RoleName;
import collabhubbr.users.models.UserEntity;
import collabhubbr.users.service.PersistenceService;
import collabhubbr.users.validations.PasswordValidations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    TokenServiceImpl tokenServiceImpl;
    @Mock
    PersistenceService persistenceService;
    @Mock
    PasswordValidations passwordValidations;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    UserEntity userEntity;
    ResponseNewUserDTO responseNewUserDTO;
    RequestUserDTO requestUserDTO;

    @BeforeEach
    void setUp() {
        requestUserDTO = RequestUserDTO.builder()
                .username("John Doe")
                .email("john@example.com")
                .password("123456789")
                .build();
        userEntity = new UserEntity(requestUserDTO);
        responseNewUserDTO = new ResponseNewUserDTO(userEntity);
        userEntity.setId(1L);
    }

    @Test
    @DisplayName("Should create a new user account")
    void case01() {
        when(persistenceService.save(any())).thenReturn(userEntity);

        var response = userServiceImpl.createAccount(requestUserDTO);

        assertAll("Response",
                () -> assertNotNull(response),
                () -> assertEquals(responseNewUserDTO.email(), response.email()),
                () -> assertEquals(responseNewUserDTO.username(), response.username())
        );
        assertAll("Verify",
                () -> verify(persistenceService, times(1))
                        .save(any(UserEntity.class)),
                () -> verify(passwordEncoder, times(1))
                        .encode(any())
        );
    }

    @Test
    @DisplayName("Should login an existing user account")
    void case02() {
        RequestLoginDTO requestLoginDTO = new RequestLoginDTO(
                requestUserDTO.email(),
                requestUserDTO.password()
        );

        when(persistenceService.findByEmail(requestUserDTO.email())).thenReturn(userEntity);
        when(tokenServiceImpl.generateToken(userEntity)).thenReturn("token123");

        var response = userServiceImpl.loginAccount(requestLoginDTO);

        assertAll("Response",
                () -> assertNotNull(response),
                () -> assertEquals(1L, response.id()),
                () -> assertEquals("token123", response.token()),
                () -> assertEquals(RoleName.PUBLIC_USER, response.role())
        );
        assertAll("Verify",
                () -> verify(persistenceService, times(1))
                        .findByEmail(requestUserDTO.email()),
                () -> verify(passwordValidations, times(1))
                        .validate(any(), any(UserEntity.class)),
                () -> verify(tokenServiceImpl, times(1))
                        .generateToken(any(UserEntity.class))
        );
    }

}