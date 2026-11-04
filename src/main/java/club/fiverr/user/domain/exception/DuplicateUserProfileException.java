package club.fiverr.user.domain.exception;

public class DuplicateUserProfileException extends UserDomainException {

    public DuplicateUserProfileException(String message) {
        super(message);
    }
}
