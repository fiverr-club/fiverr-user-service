package club.fiverr.user.application.port.in;

import club.fiverr.user.application.dto.command.CreateUserProfileCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;

public interface CreateUserProfileUseCase {

    UserProfileResult execute(CreateUserProfileCommand command);
}
