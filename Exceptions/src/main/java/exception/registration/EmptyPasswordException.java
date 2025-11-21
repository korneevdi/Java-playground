package exception.registration;

public class EmptyPasswordException extends Exception{

    public EmptyPasswordException(String message) {
        super(message);
    }
}
