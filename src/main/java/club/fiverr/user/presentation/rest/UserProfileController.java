package club.fiverr.user.presentation.rest;

import club.fiverr.user.application.dto.result.UserProfileResult;
import club.fiverr.user.application.port.in.CreateUserProfileUseCase;
import club.fiverr.user.application.port.in.GetUserProfileUseCase;
import club.fiverr.user.application.port.in.UpdateUserProfileUseCase;
import club.fiverr.user.application.port.in.UpgradeToSellerUseCase;
import club.fiverr.user.presentation.rest.mapper.UserProfileRestMapper;
import club.fiverr.user.presentation.rest.request.CreateUserProfileRequest;
import club.fiverr.user.presentation.rest.request.UpdateUserProfileRequest;
import club.fiverr.user.presentation.rest.request.UpgradeToSellerRequest;
import club.fiverr.user.presentation.rest.response.UserProfileResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UserProfileController {

    private final CreateUserProfileUseCase createUserProfileUseCase;
    private final UpdateUserProfileUseCase updateUserProfileUseCase;
    private final UpgradeToSellerUseCase upgradeToSellerUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;

    public UserProfileController(
            CreateUserProfileUseCase createUserProfileUseCase,
            UpdateUserProfileUseCase updateUserProfileUseCase,
            UpgradeToSellerUseCase upgradeToSellerUseCase,
            GetUserProfileUseCase getUserProfileUseCase) {
        this.createUserProfileUseCase = createUserProfileUseCase;
        this.updateUserProfileUseCase = updateUserProfileUseCase;
        this.upgradeToSellerUseCase = upgradeToSellerUseCase;
        this.getUserProfileUseCase = getUserProfileUseCase;
    }

    @PostMapping("/profiles")
    public ResponseEntity<UserProfileResponse> createProfile(@Valid @RequestBody CreateUserProfileRequest request) {
        UserProfileResult result = createUserProfileUseCase.execute(UserProfileRestMapper.toCommand(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserProfileRestMapper.toResponse(result));
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<UserProfileResponse> getProfileById(@PathVariable UUID id) {
        UserProfileResult result = getUserProfileUseCase.getById(id);
        return ResponseEntity.ok(UserProfileRestMapper.toResponse(result));
    }

    @GetMapping("/profiles/account/{accountId}")
    public ResponseEntity<UserProfileResponse> getProfileByAccountId(@PathVariable UUID accountId) {
        UserProfileResult result = getUserProfileUseCase.getByAccountId(accountId);
        return ResponseEntity.ok(UserProfileRestMapper.toResponse(result));
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserProfileResponse> getProfileByUsername(@PathVariable String username) {
        UserProfileResult result = getUserProfileUseCase.getByUsername(username);
        return ResponseEntity.ok(UserProfileRestMapper.toResponse(result));
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUserProfileRequest request) {
        UserProfileResult result = updateUserProfileUseCase.execute(UserProfileRestMapper.toCommand(id, request));
        return ResponseEntity.ok(UserProfileRestMapper.toResponse(result));
    }

    @PostMapping("/profiles/{id}/upgrade-seller")
    public ResponseEntity<UserProfileResponse> upgradeToSeller(
            @PathVariable UUID id,
            @Valid @RequestBody UpgradeToSellerRequest request) {
        UserProfileResult result = upgradeToSellerUseCase.execute(UserProfileRestMapper.toCommand(id, request));
        return ResponseEntity.ok(UserProfileRestMapper.toResponse(result));
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<UserProfileResponse>> listActiveSellers() {
        List<UserProfileResponse> sellers = getUserProfileUseCase.listActiveSellers().stream()
                .map(UserProfileRestMapper::toResponse)
                .toList();
        return ResponseEntity.ok(sellers);
    }
}
