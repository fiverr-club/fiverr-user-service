package club.fiverr.user.presentation.rest.response;

import club.fiverr.user.domain.model.AvailabilityStatus;
import club.fiverr.user.domain.model.SellerLevel;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record UserProfileResponse(
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
