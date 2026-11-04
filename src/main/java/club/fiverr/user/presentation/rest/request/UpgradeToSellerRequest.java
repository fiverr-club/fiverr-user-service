package club.fiverr.user.presentation.rest.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

public record UpgradeToSellerRequest(
        @NotBlank(message = "Headline is required for seller profiles")
        String headline,

        String bio,

        @NotEmpty(message = "At least one skill is required")
        List<String> skills,

        @DecimalMin(value = "0.0", message = "Hourly rate cannot be negative")
        BigDecimal hourlyRate
) {
}
