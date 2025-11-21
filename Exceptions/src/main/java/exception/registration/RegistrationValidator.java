package exception.registration;

public class RegistrationValidator {

    public static void validateRegistration(String login, String password, String confirmPassword)
            throws InvalidCharacterException, EmptyLoginException, EmptyPasswordException,
            TooLongLoginException, TooLongPasswordException, PasswordNotMatchException {

        if (!login.matches("[a-zA-Z0-9]+") || !password.matches("[a-zA-Z0-9]+")) {
            throw new InvalidCharacterException("Login and password should contain only latin letters and numbers");
        }
        if (login.isEmpty()) {
            throw new EmptyLoginException("Login should not be empty");
        }
        if (login.length() > 20) {
            throw new TooLongLoginException("Login should not be longer than 20 symbols");
        }
        if (password.isEmpty()) {
            throw new EmptyPasswordException("Password should not be empty");
        }
        if (password.length() > 20) {
            throw new TooLongPasswordException("Password should not be longer than 20 symbols");
        }
        if (!password.equals(confirmPassword)) {
            throw new PasswordNotMatchException("Password confirmation does not match with the password");
        }
    }
}
