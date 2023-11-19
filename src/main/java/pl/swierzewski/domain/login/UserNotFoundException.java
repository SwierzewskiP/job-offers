package pl.swierzewski.domain.login;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
