package club.fiverr.user.application.mapper;

import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.domain.model.UserProfile;

public final class UserProfileResultMapper {

    private UserProfileResultMapper() {
    }

    public static UserProfileResult toResult(UserProfile profile) {
        return new UserProfileResult(
                profile.getId(),
                profile.getAccountId(),
                profile.getUsername(),
                profile.getDisplayName(),
                profile.getHeadline(),
                profile.getBio(),
                profile.getAvatarUrl(),
                profile.getCountry(),
                profile.getLanguages(),
                profile.getSkills(),
                profile.getHourlyRate(),
                profile.isSeller(),
                profile.getSellerLevel(),
                profile.getAvailabilityStatus(),
                profile.getCompletedOrdersCount(),
                profile.getRatingAverage(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}
