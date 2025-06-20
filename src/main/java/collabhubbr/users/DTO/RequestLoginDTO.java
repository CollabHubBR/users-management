package collabhubbr.users.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestLoginDTO(
        @Email
        @NotBlank
        String email,
        @Size(min = 6)
        String password
) {
}
