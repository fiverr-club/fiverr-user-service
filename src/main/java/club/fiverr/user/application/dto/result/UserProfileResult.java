package club.fiverr.user.application.dto.result;

import club.fiverr.user.domain.model.AvailabilityStatus;
import club.fiverr.user.domain.model.SellerLevel;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record UserProfileResult(
        UUID id,
        UUID accountId,
        String username,
        String displayName,
        String headline,
        String bio,
        String avatarUrl,
        String country,
        List<String> languages,
        List<String> skills,
        BigDecimal hourlyRate,
        boolean seller,
        SellerLevel sellerLevel,
        AvailabilityStatus availabilityStatus,
        int completedOrdersCount,
        double ratingAverage,
        Instant createdAt,
        Instant updatedAt
) {
}
