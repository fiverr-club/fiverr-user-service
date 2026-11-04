package club.fiverr.user.domain.exception;

public class UserProfileNotFoundException extends UserDomainException {

    public UserProfileNotFoundException(String message) {
        super(message);
    }
}
