package collabhubbr.users.service;

import collabhubbr.users.models.UserEntity;

public interface PersistenceService {
    UserEntity save(UserEntity userEntity);
    UserEntity findByEmail(String email);
}
