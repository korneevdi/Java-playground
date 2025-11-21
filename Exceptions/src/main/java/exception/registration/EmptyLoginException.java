package exception.registration;

public class EmptyLoginException extends Exception{

    public EmptyLoginException(String message) {
        super(message);
    }
}
