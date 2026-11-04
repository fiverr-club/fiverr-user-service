package club.fiverr.user.application.usecase;

import club.fiverr.user.application.dto.command.UpgradeToSellerCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.application.mapper.UserProfileResultMapper;
import club.fiverr.user.application.port.in.UpgradeToSellerUseCase;
import club.fiverr.user.application.port.out.ProfileEventPublisherPort;
import club.fiverr.user.domain.exception.UserProfileNotFoundException;
import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.domain.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpgradeToSellerUseCaseImpl implements UpgradeToSellerUseCase {

    private final UserProfileRepository userProfileRepository;
    private final ProfileEventPublisherPort profileEventPublisherPort;

    public UpgradeToSellerUseCaseImpl(
            UserProfileRepository userProfileRepository,
            ProfileEventPublisherPort profileEventPublisherPort) {
        this.userProfileRepository = userProfileRepository;
        this.profileEventPublisherPort = profileEventPublisherPort;
    }

    @Override
    @Transactional
    public UserProfileResult execute(UpgradeToSellerCommand command) {
        UserProfile profile = userProfileRepository.findById(command.profileId())
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found: " + command.profileId()));

        profile.upgradeToSeller(
                command.headline(),
                command.bio(),
                command.skills(),
                command.hourlyRate()
        );

        UserProfile saved = userProfileRepository.save(profile);
        profileEventPublisherPort.publishProfileUpgradedToSeller(saved);

        return UserProfileResultMapper.toResult(saved);
    }
}
