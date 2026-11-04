package club.fiverr.user.application.usecase;

import club.fiverr.user.UserProfileTestFixtures;
import club.fiverr.user.application.dto.command.UpdateUserProfileCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.domain.model.AvailabilityStatus;
import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.domain.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserProfileUseCaseImplTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private UpdateUserProfileUseCaseImpl updateUserProfileUseCase;

    @Test
    void shouldUpdateExistingUserProfile() {
        UpdateUserProfileCommand command = UserProfileTestFixtures.createValidUpdateCommand();
        UserProfile existing = UserProfileTestFixtures.createValidBuyerProfile();

        when(userProfileRepository.findById(command.profileId())).thenReturn(Optional.of(existing));
        when(userProfileRepository.save(any(UserProfile.class))).thenAnswer(inv -> inv.getArgument(0));

        UserProfileResult result = updateUserProfileUseCase.execute(command);

        assertNotNull(result);
        assertEquals("Alex Rivera Pro", result.displayName());
        assertEquals("Canada", result.country());
        assertEquals(AvailabilityStatus.BUSY, result.availabilityStatus());
        verify(userProfileRepository).save(any(UserProfile.class));
    }
}
