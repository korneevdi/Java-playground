package exception.registration;

public class TooLongPasswordException extends Exception{

    public TooLongPasswordException(String message) {
        super(message);
    }
}
