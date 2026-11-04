package club.fiverr.user.domain.model;

import club.fiverr.user.domain.exception.UserDomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    @Test
    void shouldCreateNewBuyerProfileWithDefaults() {
        UserProfile profile = UserProfile.createNew(
                UUID.randomUUID(),
                "  Sarah_Design ",
                "Sarah Jenkins",
                "UI/UX Designer",
                "Passionate product designer",
                "https://cdn.fiverr.club/sarah.png",
                "United Kingdom",
                List.of("English"),
                List.of("Figma", "Design Systems")
        );

        assertNotNull(profile.getId());
        assertEquals("sarah_design", profile.getUsername());
        assertEquals("Sarah Jenkins", profile.getDisplayName());
        assertFalse(profile.isSeller());
        assertEquals(SellerLevel.NONE, profile.getSellerLevel());
        assertEquals(AvailabilityStatus.AVAILABLE, profile.getAvailabilityStatus());
    }

    @Test
    void shouldUpgradeBuyerProfileToNewSeller() {
        UserProfile profile = UserProfile.createNew(
                UUID.randomUUID(),
                "marco_code",
                "Marco Polo",
                "",
                "",
                "",
                "Italy",
                List.of("Italian", "English"),
                List.of()
        );

        profile.upgradeToSeller(
                "Full-Stack TypeScript & Java Engineer",
                "Building scalable SaaS platforms",
                List.of("Java", "React", "PostgreSQL"),
                new BigDecimal("65.00")
        );

        assertTrue(profile.isSeller());
        assertEquals(SellerLevel.NEW_SELLER, profile.getSellerLevel());
        assertEquals(new BigDecimal("65.00"), profile.getHourlyRate());
        assertEquals(3, profile.getSkills().size());
    }

    @Test
    void shouldRejectUpgradeToSellerWithoutHeadlineOrSkills() {
        UserProfile profile = UserProfile.createNew(
                UUID.randomUUID(),
                "john_doe",
                "John Doe",
                "",
                "",
                "",
                "USA",
                List.of("English"),
                List.of()
        );

        assertThrows(UserDomainException.class, () ->
                profile.upgradeToSeller("", "Bio", List.of("Java"), new BigDecimal("50.00")));
        assertThrows(UserDomainException.class, () ->
                profile.upgradeToSeller("Valid Headline", "Bio", List.of(), new BigDecimal("50.00")));
    }
}
