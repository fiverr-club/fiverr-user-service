package club.fiverr.user.domain.model;

import club.fiverr.user.domain.exception.UserDomainException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserProfile {

    private final UUID id;
    private final UUID accountId;
    private final String username;
    private String displayName;
    private String headline;
    private String bio;
    private String avatarUrl;
    private String country;
    private List<String> languages;
    private List<String> skills;
    private BigDecimal hourlyRate;
    private boolean seller;
    private SellerLevel sellerLevel;
    private AvailabilityStatus availabilityStatus;
    private int completedOrdersCount;
    private double ratingAverage;
    private final Instant createdAt;
    private Instant updatedAt;

    private UserProfile(
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
            Instant updatedAt) {
        this.id = Objects.requireNonNull(id, "Profile ID must not be null");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null");
        this.username = validateUsername(username);
        this.displayName = validateDisplayName(displayName);
        this.headline = headline != null ? headline.trim() : "";
        this.bio = bio != null ? bio.trim() : "";
        this.avatarUrl = avatarUrl != null ? avatarUrl.trim() : "";
        this.country = country != null ? country.trim() : "";
        this.languages = languages == null ? new ArrayList<>() : new ArrayList<>(languages);
        this.skills = skills == null ? new ArrayList<>() : new ArrayList<>(skills);
        this.hourlyRate = validateHourlyRate(hourlyRate);
        this.seller = seller;
        this.sellerLevel = Objects.requireNonNull(sellerLevel, "Seller level must not be null");
        this.availabilityStatus = Objects.requireNonNull(availabilityStatus, "Availability status must not be null");
        this.completedOrdersCount = Math.max(0, completedOrdersCount);
        this.ratingAverage = Math.max(0.0, ratingAverage);
        this.createdAt = Objects.requireNonNull(createdAt, "Created timestamp must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "Updated timestamp must not be null");
    }

    public static UserProfile createNew(
            UUID accountId,
            String username,
            String displayName,
            String headline,
            String bio,
            String avatarUrl,
            String country,
            List<String> languages,
            List<String> skills) {
        Instant now = Instant.now();
        return new UserProfile(
                UUID.randomUUID(),
                accountId,
                username,
                displayName,
                headline,
                bio,
                avatarUrl,
                country,
                languages,
                skills,
                BigDecimal.ZERO,
                false,
                SellerLevel.NONE,
                AvailabilityStatus.AVAILABLE,
                0,
                0.0,
                now,
                now
        );
    }

    public static UserProfile restore(
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
            Instant updatedAt) {
        return new UserProfile(
                id,
                accountId,
                username,
                displayName,
                headline,
                bio,
                avatarUrl,
                country,
                languages,
                skills,
                hourlyRate,
                seller,
                sellerLevel,
                availabilityStatus,
                completedOrdersCount,
                ratingAverage,
                createdAt,
                updatedAt
        );
    }

    public void updateDetails(
            String displayName,
            String headline,
            String bio,
            String avatarUrl,
            String country,
            List<String> languages,
            List<String> skills,
            AvailabilityStatus availabilityStatus) {
        this.displayName = validateDisplayName(displayName);
        if (headline != null) {
            this.headline = headline.trim();
        }
        if (bio != null) {
            this.bio = bio.trim();
        }
        if (avatarUrl != null) {
            this.avatarUrl = avatarUrl.trim();
        }
        if (country != null) {
            this.country = country.trim();
        }
        if (languages != null) {
            this.languages = new ArrayList<>(languages);
        }
        if (skills != null) {
            this.skills = new ArrayList<>(skills);
        }
        if (availabilityStatus != null) {
            this.availabilityStatus = availabilityStatus;
        }
        this.updatedAt = Instant.now();
    }

    public void upgradeToSeller(String headline, String bio, List<String> skills, BigDecimal hourlyRate) {
        if (headline == null || headline.isBlank()) {
            throw new UserDomainException("Seller profile requires a professional headline");
        }
        if (skills == null || skills.isEmpty()) {
            throw new UserDomainException("Seller profile requires at least one skill");
        }
        this.headline = headline.trim();
        if (bio != null && !bio.isBlank()) {
            this.bio = bio.trim();
        }
        this.skills = new ArrayList<>(skills);
        this.hourlyRate = validateHourlyRate(hourlyRate);
        this.seller = true;
        if (this.sellerLevel == SellerLevel.NONE) {
            this.sellerLevel = SellerLevel.NEW_SELLER;
        }
        this.updatedAt = Instant.now();
    }

    private static String validateUsername(String username) {
        if (username == null || username.isBlank() || username.trim().length() < 3) {
            throw new UserDomainException("Username must be at least 3 characters long");
        }
        return username.trim().toLowerCase();
    }

    private static String validateDisplayName(String displayName) {
        if (displayName == null || displayName.isBlank()) {
            throw new UserDomainException("Display name must not be blank");
        }
        return displayName.trim();
    }

    private static BigDecimal validateHourlyRate(BigDecimal hourlyRate) {
        if (hourlyRate == null) {
            return BigDecimal.ZERO;
        }
        if (hourlyRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new UserDomainException("Hourly rate cannot be negative");
        }
        return hourlyRate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getHeadline() {
        return headline;
    }

    public String getBio() {
        return bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public List<String> getLanguages() {
        return Collections.unmodifiableList(languages);
    }

    public List<String> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public boolean isSeller() {
        return seller;
    }

    public SellerLevel getSellerLevel() {
        return sellerLevel;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public int getCompletedOrdersCount() {
        return completedOrdersCount;
    }

    public double getRatingAverage() {
        return ratingAverage;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
