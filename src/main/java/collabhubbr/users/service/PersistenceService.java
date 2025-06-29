package collabhubbr.users.service;

import collabhubbr.users.controller.DTO.ResponseUserDTO;
import collabhubbr.users.models.UserEntity;

public interface PersistenceService {
    UserEntity save(UserEntity userEntity);
    UserEntity findByEmail(String email);
    ResponseUserDTO findUserById(Long id);
}
