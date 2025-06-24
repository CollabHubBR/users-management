package collabhubbr.users.strategy.impl;

import collabhubbr.users.exceptions.TokenException;
import collabhubbr.users.strategy.RecoverEmailValidations;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;

@Log4j2
public class ExpirationTokenStrategy implements RecoverEmailValidations {

    @Override
    public void validate(DecodedJWT verifiedJwt) {
        if (verifiedJwt.getExpiresAt() == null || verifiedJwt.getExpiresAt().toInstant().isBefore(Instant.now())) {
            log.error("Token expiration date is null");
            throw new TokenException("Token expiration date is null");
        }
    }

}
