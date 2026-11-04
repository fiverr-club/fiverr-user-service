package club.fiverr.user.application.usecase;

import club.fiverr.user.application.dto.command.CreateUserProfileCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.application.mapper.UserProfileResultMapper;
import club.fiverr.user.application.port.in.CreateUserProfileUseCase;
import club.fiverr.user.application.port.out.ProfileEventPublisherPort;
import club.fiverr.user.domain.exception.DuplicateUserProfileException;
import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.domain.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserProfileUseCaseImpl implements CreateUserProfileUseCase {

    private final UserProfileRepository userProfileRepository;
    private final ProfileEventPublisherPort profileEventPublisherPort;

    public CreateUserProfileUseCaseImpl(
            UserProfileRepository userProfileRepository,
            ProfileEventPublisherPort profileEventPublisherPort) {
        this.userProfileRepository = userProfileRepository;
        this.profileEventPublisherPort = profileEventPublisherPort;
    }

    @Override
    @Transactional
    public UserProfileResult execute(CreateUserProfileCommand command) {
        String normalizedUsername = command.username().trim().toLowerCase();

        if (userProfileRepository.existsByAccountId(command.accountId())) {
            throw new DuplicateUserProfileException("Profile already exists for account: " + command.accountId());
        }
        if (userProfileRepository.existsByUsername(normalizedUsername)) {
            throw new DuplicateUserProfileException("Username '" + normalizedUsername + "' is already in use");
        }

        UserProfile profile = UserProfile.createNew(
                command.accountId(),
                normalizedUsername,
                command.displayName(),
                command.headline(),
                command.bio(),
                command.avatarUrl(),
                command.country(),
                command.languages(),
                command.skills()
        );

        UserProfile saved = userProfileRepository.save(profile);
        profileEventPublisherPort.publishProfileCreated(saved);

        return UserProfileResultMapper.toResult(saved);
    }
}
