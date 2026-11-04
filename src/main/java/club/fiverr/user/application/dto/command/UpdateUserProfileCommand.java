package club.fiverr.user.application.dto.command;

import club.fiverr.user.domain.model.AvailabilityStatus;

import java.util.List;
import java.util.UUID;

public record UpdateUserProfileCommand(
        UUID profileId,
        String displayName,
        String headline,
        String bio,
        String avatarUrl,
        String country,
        List<String> languages,
        List<String> skills,
        AvailabilityStatus availabilityStatus
) {
}
