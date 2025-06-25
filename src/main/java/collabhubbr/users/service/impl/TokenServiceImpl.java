package collabhubbr.users.service.impl;

import collabhubbr.users.exceptions.TokenException;
import collabhubbr.users.models.UserEntity;
import collabhubbr.users.service.TokenService;
import collabhubbr.users.strategy.RecoverEmailValidations;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    private final List<RecoverEmailValidations> validations;

    @Override
    public String generateToken(UserEntity user) {
        try {
            log.debug("Generating token for user: {}", user.getEmail());
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            log.error("Error while generating token for user: {}", user.getEmail(), e);
            throw new TokenException("Error while authenticating");
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            log.debug("Validating token: {}", token);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            log.error("Error while validating token: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public String recoverEmail(String token) {
        try {
            log.debug("Recovering email from token: {}", token);

            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .build();
            DecodedJWT verifiedJwt = verifier.verify(token);
            log.debug("Token verified successfully");

            this.validations.forEach(v -> v.validate(verifiedJwt));

            return verifiedJwt.getSubject();
        } catch (JWTVerificationException ex) {
            log.error("Error while recovering email from token: {}", ex.getMessage());
            throw new TokenException("Error while recovering email from token", ex);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(23).toInstant(ZoneOffset.of("-03:00"));
    }
}