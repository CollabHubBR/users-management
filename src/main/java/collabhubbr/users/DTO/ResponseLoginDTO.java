package collabhubbr.users.DTO;

import collabhubbr.users.models.RoleName;
import collabhubbr.users.models.UserEntity;

public record ResponseLoginDTO(
        Long id,
        String token,
        RoleName role
) {
    public ResponseLoginDTO(UserEntity user, String token) {
        this(user.getId(), token, user.getRoles());
    }
}
