package club.fiverr.user.application.usecase;

import club.fiverr.user.application.dto.command.UpdateUserProfileCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.application.mapper.UserProfileResultMapper;
import club.fiverr.user.application.port.in.UpdateUserProfileUseCase;
import club.fiverr.user.domain.exception.UserProfileNotFoundException;
import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.domain.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateUserProfileUseCaseImpl implements UpdateUserProfileUseCase {

    private final UserProfileRepository userProfileRepository;

    public UpdateUserProfileUseCaseImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    @Transactional
    public UserProfileResult execute(UpdateUserProfileCommand command) {
        UserProfile profile = userProfileRepository.findById(command.profileId())
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found: " + command.profileId()));

        profile.updateDetails(
                command.displayName(),
                command.headline(),
                command.bio(),
                command.avatarUrl(),
                command.country(),
                command.languages(),
                command.skills(),
                command.availabilityStatus()
        );

        UserProfile saved = userProfileRepository.save(profile);
        return UserProfileResultMapper.toResult(saved);
    }
}
