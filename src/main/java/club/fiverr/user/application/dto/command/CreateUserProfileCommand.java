package club.fiverr.user.application.dto.command;

import java.util.List;
import java.util.UUID;

public record CreateUserProfileCommand(
        UUID accountId,
        String username,
        String displayName,
        String headline,
        String bio,
        String avatarUrl,
        String country,
        List<String> languages,
        List<String> skills
) {
}
