package club.fiverr.user.application.port.in;

import club.fiverr.user.application.dto.command.UpdateUserProfileCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;

public interface UpdateUserProfileUseCase {

    UserProfileResult execute(UpdateUserProfileCommand command);
}
