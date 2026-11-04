package club.fiverr.user;

import club.fiverr.user.application.dto.command.CreateUserProfileCommand;
import club.fiverr.user.application.dto.command.UpdateUserProfileCommand;
import club.fiverr.user.application.dto.command.UpgradeToSellerCommand;
import club.fiverr.user.domain.model.AvailabilityStatus;
import club.fiverr.user.domain.model.SellerLevel;
import club.fiverr.user.domain.model.UserProfile;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public final class UserProfileTestFixtures {

    public static final UUID DEFAULT_PROFILE_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee");
    public static final UUID DEFAULT_ACCOUNT_ID = UUID.fromString("11111111-2222-3333-4444-555555555555");

    private UserProfileTestFixtures() {
    }

    public static UserProfile createValidBuyerProfile() {
        Instant now = Instant.now();
        return UserProfile.restore(
                DEFAULT_PROFILE_ID,
                DEFAULT_ACCOUNT_ID,
                "alex_dev",
                "Alex Rivera",
                "Cloud Native Architect",
                "10+ years building distributed systems on Kubernetes.",
                "https://cdn.fiverr.club/avatars/alex.png",
                "United States",
                List.of("English", "Spanish"),
                List.of("Java", "Spring Boot", "Kubernetes"),
                BigDecimal.ZERO,
                false,
                SellerLevel.NONE,
                AvailabilityStatus.AVAILABLE,
                0,
                0.0,
                now.minusSeconds(3600),
                now
        );
    }

    public static CreateUserProfileCommand createValidCreateCommand() {
        return new CreateUserProfileCommand(
                DEFAULT_ACCOUNT_ID,
                "alex_dev",
                "Alex Rivera",
                "Cloud Native Architect",
                "10+ years building distributed systems on Kubernetes.",
                "https://cdn.fiverr.club/avatars/alex.png",
                "United States",
                List.of("English", "Spanish"),
                List.of("Java", "Spring Boot", "Kubernetes")
        );
    }

    public static UpdateUserProfileCommand createValidUpdateCommand() {
        return new UpdateUserProfileCommand(
                DEFAULT_PROFILE_ID,
                "Alex Rivera Pro",
                "Principal Distributed Systems Engineer",
                "Updated biography for freelance marketplace.",
                "https://cdn.fiverr.club/avatars/alex-v2.png",
                "Canada",
                List.of("English", "French"),
                List.of("Java 21", "Kafka", "PostgreSQL"),
                AvailabilityStatus.BUSY
        );
    }

    public static UpgradeToSellerCommand createValidUpgradeToSellerCommand() {
        return new UpgradeToSellerCommand(
                DEFAULT_PROFILE_ID,
                "Senior Java & Microservices Consultant",
                "Delivering production-grade Spring Boot microservices.",
                List.of("Java 21", "Spring Boot", "DDD", "Kubernetes"),
                new BigDecimal("85.00")
        );
    }
}
