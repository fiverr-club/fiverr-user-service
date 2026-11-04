package club.fiverr.user.application.usecase;

import club.fiverr.user.UserProfileTestFixtures;
import club.fiverr.user.application.dto.command.CreateUserProfileCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.application.port.out.ProfileEventPublisherPort;
import club.fiverr.user.domain.exception.DuplicateUserProfileException;
import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.domain.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserProfileUseCaseImplTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private ProfileEventPublisherPort profileEventPublisherPort;

    @InjectMocks
    private CreateUserProfileUseCaseImpl createUserProfileUseCase;

    @Test
    void shouldSuccessfullyCreateUserProfile() {
        CreateUserProfileCommand command = UserProfileTestFixtures.createValidCreateCommand();
        when(userProfileRepository.existsByAccountId(command.accountId())).thenReturn(false);
        when(userProfileRepository.existsByUsername("alex_dev")).thenReturn(false);
        when(userProfileRepository.save(any(UserProfile.class))).thenAnswer(inv -> inv.getArgument(0));

        UserProfileResult result = createUserProfileUseCase.execute(command);

        assertNotNull(result);
        assertEquals("alex_dev", result.username());
        assertEquals("Alex Rivera", result.displayName());
        assertFalse(result.seller());
        verify(userProfileRepository).save(any(UserProfile.class));
        verify(profileEventPublisherPort).publishProfileCreated(any(UserProfile.class));
    }

    @Test
    void shouldThrowDuplicateExceptionWhenAccountAlreadyHasProfile() {
        CreateUserProfileCommand command = UserProfileTestFixtures.createValidCreateCommand();
        when(userProfileRepository.existsByAccountId(command.accountId())).thenReturn(true);

        assertThrows(DuplicateUserProfileException.class, () -> createUserProfileUseCase.execute(command));
        verify(userProfileRepository, never()).save(any());
    }
}
