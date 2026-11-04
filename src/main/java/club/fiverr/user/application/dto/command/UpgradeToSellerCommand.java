package club.fiverr.user.application.dto.command;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record UpgradeToSellerCommand(
        UUID profileId,
        String headline,
        String bio,
        List<String> skills,
        BigDecimal hourlyRate
) {
}
