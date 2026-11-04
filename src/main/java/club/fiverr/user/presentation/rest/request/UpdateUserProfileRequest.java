package club.fiverr.user.presentation.rest.request;

import club.fiverr.user.domain.model.AvailabilityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateUserProfileRequest(
        @NotBlank(message = "Display name is required")
        @Size(max = 128)
        String displayName,

        @Size(max = 255)
        String headline,

        @Size(max = 2000)
        String bio,

        String avatarUrl,

        String country,

        List<String> languages,

        List<String> skills,

        AvailabilityStatus availabilityStatus
) {
}
