package collabhubbr.users.controller.DTO;

import jakarta.validation.constraints.NotBlank;

public record RequestMailDTO(
        @NotBlank
        String toMail
) {
}
