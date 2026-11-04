package club.fiverr.user.application.usecase;

import club.fiverr.user.UserProfileTestFixtures;
import club.fiverr.user.application.dto.command.UpgradeToSellerCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.application.port.out.ProfileEventPublisherPort;
import club.fiverr.user.domain.model.SellerLevel;
import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.domain.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpgradeToSellerUseCaseImplTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private ProfileEventPublisherPort profileEventPublisherPort;

    @InjectMocks
    private UpgradeToSellerUseCaseImpl upgradeToSellerUseCase;

    @Test
    void shouldUpgradeBuyerProfileToSellerAndPublishEvent() {
        UpgradeToSellerCommand command = UserProfileTestFixtures.createValidUpgradeToSellerCommand();
        UserProfile existing = UserProfileTestFixtures.createValidBuyerProfile();

        when(userProfileRepository.findById(command.profileId())).thenReturn(Optional.of(existing));
        when(userProfileRepository.save(any(UserProfile.class))).thenAnswer(inv -> inv.getArgument(0));

        UserProfileResult result = upgradeToSellerUseCase.execute(command);

        assertNotNull(result);
        assertTrue(result.seller());
        assertEquals(SellerLevel.NEW_SELLER, result.sellerLevel());
        assertEquals(new BigDecimal("85.00"), result.hourlyRate());
        verify(userProfileRepository).save(any(UserProfile.class));
        verify(profileEventPublisherPort).publishProfileUpgradedToSeller(any(UserProfile.class));
    }
}
