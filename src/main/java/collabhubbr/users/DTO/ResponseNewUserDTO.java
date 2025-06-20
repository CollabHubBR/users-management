package collabhubbr.users.DTO;

import collabhubbr.users.models.UserEntity;

public record ResponseNewUserDTO(
        String username,
        String email
){
    public ResponseNewUserDTO(UserEntity user) {
        this(user.getPassword(), user.getEmail());
    }
}
