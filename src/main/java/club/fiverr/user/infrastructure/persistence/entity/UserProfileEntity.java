package club.fiverr.user.infrastructure.persistence.entity;

import club.fiverr.user.domain.model.AvailabilityStatus;
import club.fiverr.user.domain.model.SellerLevel;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
public class UserProfileEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "account_id", nullable = false, unique = true, updatable = false)
    private UUID accountId;

    @Column(name = "username", nullable = false, unique = true, length = 64)
    private String username;

    @Column(name = "display_name", nullable = false, length = 128)
    private String displayName;

    @Column(name = "headline", length = 255)
    private String headline;

    @Column(name = "bio", length = 2000)
    private String bio;

    @Column(name = "avatar_url", length = 512)
    private String avatarUrl;

    @Column(name = "country", length = 64)
    private String country;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_profile_languages", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "language", nullable = false, length = 64)
    private List<String> languages = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_profile_skills", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "skill", nullable = false, length = 64)
    private List<String> skills = new ArrayList<>();

    @Column(name = "hourly_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    @Column(name = "is_seller", nullable = false)
    private boolean seller;

    @Enumerated(EnumType.STRING)
    @Column(name = "seller_level", nullable = false, length = 32)
    private SellerLevel sellerLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status", nullable = false, length = 32)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "completed_orders_count", nullable = false)
    private int completedOrdersCount;

    @Column(name = "rating_average", nullable = false)
    private double ratingAverage;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public UserProfileEntity() {
    }

    public UserProfileEntity(
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
        this.id = id;
        this.accountId = accountId;
        this.username = username;
        this.displayName = displayName;
        this.headline = headline;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.country = country;
        this.languages = new ArrayList<>(languages);
        this.skills = new ArrayList<>(skills);
        this.hourlyRate = hourlyRate;
        this.seller = seller;
        this.sellerLevel = sellerLevel;
        this.availabilityStatus = availabilityStatus;
        this.completedOrdersCount = completedOrdersCount;
        this.ratingAverage = ratingAverage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public boolean isSeller() {
        return seller;
    }

    public void setSeller(boolean seller) {
        this.seller = seller;
    }

    public SellerLevel getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(SellerLevel sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public int getCompletedOrdersCount() {
        return completedOrdersCount;
    }

    public void setCompletedOrdersCount(int completedOrdersCount) {
        this.completedOrdersCount = completedOrdersCount;
    }

    public double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
