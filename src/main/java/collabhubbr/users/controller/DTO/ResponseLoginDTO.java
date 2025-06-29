package collabhubbr.users.controller.DTO;

import collabhubbr.users.models.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(example = """
        {
          "id": 1,
          "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJsb2dpbi1hdXRoLWFwaSIsInN1YiI6ImpvaG5AZXh..."
        }
        """)
public record ResponseLoginDTO(
        Long id,
        String token
) {
    public ResponseLoginDTO(UserEntity user, String token) {
        this(user.getId(), token);
    }
}
