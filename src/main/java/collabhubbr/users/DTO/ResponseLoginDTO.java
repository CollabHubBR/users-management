package collabhubbr.users.DTO;

import collabhubbr.users.models.RoleName;

public record ResponseLoginDTO(
        Long id,
        String token,
        RoleName role
) {
}
