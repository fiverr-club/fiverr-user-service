package club.fiverr.user.presentation.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record CreateUserProfileRequest(
        @NotNull(message = "Account ID is required")
        UUID accountId,

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 64, message = "Username must be between 3 and 64 characters")
        String username,

        @NotBlank(message = "Display name is required")
        @Size(max = 128, message = "Display name must not exceed 128 characters")
        String displayName,

        @Size(max = 255)
        String headline,

        @Size(max = 2000)
        String bio,

        String avatarUrl,

        String country,

        List<String> languages,

        List<String> skills
) {
}
