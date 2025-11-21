package exception.registration;

public class TooLongLoginException extends Exception{

    public TooLongLoginException(String message) {
        super(message);
    }
}
