package club.fiverr.user.infrastructure.persistence.repository;

import club.fiverr.user.domain.model.UserProfile;
import club.fiverr.user.domain.repository.UserProfileRepository;
import club.fiverr.user.infrastructure.persistence.entity.UserProfileEntity;
import club.fiverr.user.infrastructure.persistence.mapper.UserProfilePersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserProfileRepository implements UserProfileRepository {

    private final UserProfileJpaRepository jpaRepository;
    private final UserProfilePersistenceMapper mapper;

    public JpaUserProfileRepository(
            UserProfileJpaRepository jpaRepository,
            UserProfilePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        UserProfileEntity entity = mapper.toEntity(userProfile);
        UserProfileEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<UserProfile> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<UserProfile> findByAccountId(UUID accountId) {
        return jpaRepository.findByAccountId(accountId).map(mapper::toDomain);
    }

    @Override
    public Optional<UserProfile> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public List<UserProfile> findActiveSellers() {
        return jpaRepository.findBySellerTrue().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<UserProfile> findAllSellers() {
        return findActiveSellers();
    }

    @Override
    public boolean existsByAccountId(UUID accountId) {
        return jpaRepository.existsByAccountId(accountId);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }
}
