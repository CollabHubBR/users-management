package collabhubbr.users.validations;

import collabhubbr.users.controller.DTO.RequestLoginDTO;
import collabhubbr.users.models.UserEntity;

public interface PasswordValidations {
    void validate(RequestLoginDTO user, UserEntity userEntity);
}
