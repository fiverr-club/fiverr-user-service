package club.fiverr.user.application.port.out;

import club.fiverr.user.domain.model.UserProfile;

public interface ProfileEventPublisherPort {

    void publishProfileCreated(UserProfile profile);

    void publishProfileUpgradedToSeller(UserProfile profile);
}
