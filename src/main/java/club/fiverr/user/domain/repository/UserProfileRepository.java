package club.fiverr.user.domain.repository;

import club.fiverr.user.domain.model.UserProfile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository {

    UserProfile save(UserProfile userProfile);

    Optional<UserProfile> findById(UUID id);

    Optional<UserProfile> findByAccountId(UUID accountId);

    Optional<UserProfile> findByUsername(String username);

    List<UserProfile> findActiveSellers();

    boolean existsByAccountId(UUID accountId);

    boolean existsByUsername(String username);
}
