package club.fiverr.user.application.usecase;

import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.application.mapper.UserProfileResultMapper;
import club.fiverr.user.application.port.in.GetUserProfileUseCase;
import club.fiverr.user.domain.exception.UserProfileNotFoundException;
import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.domain.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GetUserProfileUseCaseImpl implements GetUserProfileUseCase {

    private final UserProfileRepository userProfileRepository;

    public GetUserProfileUseCaseImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileResult getById(UUID profileId) {
        UserProfile profile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found: " + profileId));
        return UserProfileResultMapper.toResult(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileResult getByAccountId(UUID accountId) {
        UserProfile profile = userProfileRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found for account: " + accountId));
        return UserProfileResultMapper.toResult(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileResult getByUsername(String username) {
        UserProfile profile = userProfileRepository.findByUsername(username.trim().toLowerCase())
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found for username: " + username));
        return UserProfileResultMapper.toResult(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfileResult> listActiveSellers() {
        return userProfileRepository.findActiveSellers().stream()
                .map(UserProfileResultMapper::toResult)
                .toList();
    }
}
