package club.fiverr.user.application.port.in;

import club.fiverr.user.application.dto.command.UpgradeToSellerCommand;
import club.fiverr.user.application.dto.result.UserProfileResult;

public interface UpgradeToSellerUseCase {

    UserProfileResult execute(UpgradeToSellerCommand command);
}
