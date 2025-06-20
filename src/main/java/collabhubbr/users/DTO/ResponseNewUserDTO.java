package collabhubbr.users.DTO;

import collabhubbr.users.models.UserEntity;

public record ResponseNewUserDTO(
        String password,
        String email
){
    public ResponseNewUserDTO(UserEntity user) {
        this(user.getPassword(), user.getEmail());
    }
}
