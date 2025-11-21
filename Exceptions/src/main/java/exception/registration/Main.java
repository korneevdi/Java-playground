package exception.registration;

public class Main {

    private static String login = "login";
    private static String password = "e-";
    private static String confirmPassword = "er";

    public static void main(String[] args) {
        try{
            RegistrationValidator.validateRegistration(login, password, confirmPassword);
        } catch (InvalidCharacterException | EmptyLoginException | EmptyPasswordException |
                TooLongLoginException | TooLongPasswordException | PasswordNotMatchException e) {
            System.out.println(e.getMessage());
        }
    }
}
