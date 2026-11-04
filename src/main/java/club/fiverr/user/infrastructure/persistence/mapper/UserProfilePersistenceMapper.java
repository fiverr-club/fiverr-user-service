package club.fiverr.user.infrastructure.persistence.mapper;

import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.infrastructure.persistence.entity.UserProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class UserProfilePersistenceMapper {

    public UserProfileEntity toEntity(UserProfile domain) {
        return new UserProfileEntity(
                domain.getId(),
                domain.getAccountId(),
                domain.getUsername(),
                domain.getDisplayName(),
                domain.getHeadline(),
                domain.getBio(),
                domain.getAvatarUrl(),
                domain.getCountry(),
                domain.getLanguages(),
                domain.getSkills(),
                domain.getHourlyRate(),
                domain.isSeller(),
                domain.getSellerLevel(),
                domain.getAvailabilityStatus(),
                domain.getCompletedOrdersCount(),
                domain.getRatingAverage(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    public UserProfile toDomain(UserProfileEntity entity) {
        return UserProfile.restore(
                entity.getId(),
                entity.getAccountId(),
                entity.getUsername(),
                entity.getDisplayName(),
                entity.getHeadline(),
                entity.getBio(),
                entity.getAvatarUrl(),
                entity.getCountry(),
                entity.getLanguages(),
                entity.getSkills(),
                entity.getHourlyRate(),
                entity.isSeller(),
                entity.getSellerLevel(),
                entity.getAvailabilityStatus(),
                entity.getCompletedOrdersCount(),
                entity.getRatingAverage(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
