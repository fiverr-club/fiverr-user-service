package club.fiverr.user.application.port.in;

import club.fiverr.user.application.dto.result.UserProfileResult;

import java.util.List;
import java.util.UUID;

public interface GetUserProfileUseCase {

    UserProfileResult getById(UUID profileId);

    UserProfileResult getByAccountId(UUID accountId);

    UserProfileResult getByUsername(String username);

    List<UserProfileResult> listActiveSellers();
}
