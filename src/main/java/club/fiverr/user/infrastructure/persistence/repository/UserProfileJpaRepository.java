package club.fiverr.user.infrastructure.persistence.repository;

import club.fiverr.user.infrastructure.persistence.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserProfileJpaRepository extends JpaRepository<UserProfileEntity, UUID> {

    Optional<UserProfileEntity> findByAccountId(UUID accountId);

    Optional<UserProfileEntity> findByUsername(String username);

    List<UserProfileEntity> findBySellerTrue();

    boolean existsByAccountId(UUID accountId);

    boolean existsByUsername(String username);
}
