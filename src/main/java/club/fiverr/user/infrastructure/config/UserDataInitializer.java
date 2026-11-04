package club.fiverr.user.infrastructure.config;

import club.fiverr.user.domain.model.AvailabilityStatus;
import club.fiverr.user.domain.model.SellerLevel;
import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.domain.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class UserDataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserDataInitializer.class);

    public static final UUID ALEX_PROFILE_ID = UUID.fromString("aaaaaaaa-1111-1111-1111-111111111111");
    public static final UUID ELENA_PROFILE_ID = UUID.fromString("aaaaaaaa-2222-2222-2222-222222222222");
    public static final UUID JORDAN_PROFILE_ID = UUID.fromString("aaaaaaaa-3333-3333-3333-333333333333");

    public static final UUID ALEX_ACCOUNT_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    public static final UUID ELENA_ACCOUNT_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");
    public static final UUID JORDAN_ACCOUNT_ID = UUID.fromString("33333333-3333-3333-3333-333333333333");

    private final UserProfileRepository userProfileRepository;

    public UserDataInitializer(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void run(String... args) {
        try {
            if (!userProfileRepository.findAllSellers().isEmpty()) {
                log.info("User profiles already seeded, skipping initialization.");
                return;
            }

            Instant now = Instant.now();

            UserProfile alex = UserProfile.restore(
                    ALEX_PROFILE_ID,
                    ALEX_ACCOUNT_ID,
                    "alex_architect",
                    "Alex Rivera",
                    "Principal Distributed Systems & Cloud Native Architect",
                    "12+ years architecting high-throughput Spring Boot, Kafka, and Kubernetes platforms for FinTech and AI startups.",
                    "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=256&q=80",
                    "United States",
                    List.of("English", "Spanish"),
                    List.of("Java 21", "Spring Boot", "DDD", "Kafka", "Kubernetes", "PostgreSQL"),
                    new BigDecimal("185.00"),
                    true,
                    SellerLevel.TOP_RATED,
                    AvailabilityStatus.AVAILABLE,
                    148,
                    4.98,
                    now,
                    now
            );

            UserProfile elena = UserProfile.restore(
                    ELENA_PROFILE_ID,
                    ELENA_ACCOUNT_ID,
                    "elena_ai",
                    "Elena Vance",
                    "Staff AI Systems Engineer & LLM Infrastructure Specialist",
                    "Building production RAG pipelines, multi-agent orchestrators, and low-latency inference services.",
                    "https://images.unsplash.com/photo-1580489944761-15a19d654956?w=256&q=80",
                    "United Kingdom",
                    List.of("English", "German"),
                    List.of("Python", "LangGraph", "Vector Databases", "FastAPI", "Next.js", "TypeScript"),
                    new BigDecimal("160.00"),
                    true,
                    SellerLevel.LEVEL_TWO,
                    AvailabilityStatus.AVAILABLE,
                    86,
                    4.95,
                    now,
                    now
            );

            UserProfile jordan = UserProfile.restore(
                    JORDAN_PROFILE_ID,
                    JORDAN_ACCOUNT_ID,
                    "jordan_taylor",
                    "Jordan Taylor",
                    "Product Design Director & Full-Stack Design Engineer",
                    "Crafting Swiss-grid editorial interfaces, design systems, and interactive web experiences.",
                    "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=256&q=80",
                    "Canada",
                    List.of("English", "French"),
                    List.of("Figma", "Next.js", "Tailwind CSS", "Design Systems", "Framer Motion"),
                    new BigDecimal("125.00"),
                    true,
                    SellerLevel.LEVEL_ONE,
                    AvailabilityStatus.AVAILABLE,
                    39,
                    4.92,
                    now,
                    now
            );

            userProfileRepository.save(alex);
            userProfileRepository.save(elena);
            userProfileRepository.save(jordan);
            log.info("Seeded 3 default FIVERREX user/seller profiles.");
        } catch (Exception ex) {
            log.warn("Failed to seed default user profiles (non-fatal): {}", ex.getMessage());
        }
    }
}
