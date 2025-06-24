package collabhubbr.users.strategy.impl;

import collabhubbr.users.exceptions.TokenException;
import collabhubbr.users.strategy.RecoverEmailValidations;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class IssuerTokenStrategy implements RecoverEmailValidations {

    @Override
    public void validate(DecodedJWT verifiedJwt) {
        if (!verifiedJwt.getIssuer().equals("login-auth-api")) {
            log.error("Invalid token issuer: {}", verifiedJwt.getIssuer());
            throw new TokenException("Invalid token issuer");
        }
    }

}
