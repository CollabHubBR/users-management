package collabhubbr.users.service;

import collabhubbr.users.models.UserEntity;

public interface TokenService {
    String generateToken(UserEntity user);
    String validateToken(String token);
}
