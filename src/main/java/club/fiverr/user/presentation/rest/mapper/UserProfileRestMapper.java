package club.fiverr.user.presentation.rest.mapper;

import club.fiverr.user.application.dto.command.CreateUserProfileCommand;
import club.fiverr.user.application.dto.command.UpdateUserProfileCommand;
import club.fiverr.user.application.dto.command.UpgradeToSellerCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.presentation.rest.request.CreateUserProfileRequest;
import club.fiverr.user.presentation.rest.request.UpdateUserProfileRequest;
import club.fiverr.user.presentation.rest.request.UpgradeToSellerRequest;
import club.fiverr.user.presentation.rest.response.UserProfileResponse;

import java.util.UUID;

public final class UserProfileRestMapper {

    private UserProfileRestMapper() {
    }

    public static CreateUserProfileCommand toCommand(CreateUserProfileRequest request) {
        return new CreateUserProfileCommand(
                request.accountId(),
                request.username(),
                request.displayName(),
                request.headline(),
                request.bio(),
                request.avatarUrl(),
                request.country(),
                request.languages(),
                request.skills()
        );
    }

    public static UpdateUserProfileCommand toCommand(UUID profileId, UpdateUserProfileRequest request) {
        return new UpdateUserProfileCommand(
                profileId,
                request.displayName(),
                request.headline(),
                request.bio(),
                request.avatarUrl(),
                request.country(),
                request.languages(),
                request.skills(),
                request.availabilityStatus()
        );
    }

    public static UpgradeToSellerCommand toCommand(UUID profileId, UpgradeToSellerRequest request) {
        return new UpgradeToSellerCommand(
                profileId,
                request.headline(),
                request.bio(),
                request.skills(),
                request.hourlyRate()
        );
    }

    public static UserProfileResponse toResponse(UserProfileResult result) {
        return new UserProfileResponse(
                result.id(),
                result.accountId(),
                result.username(),
                result.displayName(),
                result.headline(),
                result.bio(),
                result.avatarUrl(),
                result.country(),
                result.languages(),
                result.skills(),
                result.hourlyRate(),
                result.seller(),
                result.sellerLevel(),
                result.availabilityStatus(),
                result.completedOrdersCount(),
                result.ratingAverage(),
                result.createdAt(),
                result.updatedAt()
        );
    }
}
