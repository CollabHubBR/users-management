package collabhubbr.users.strategy;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface RecoverEmailValidations {
    void validate(DecodedJWT verifiedJwt);
}
