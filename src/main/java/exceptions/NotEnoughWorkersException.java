package exceptions;

public class NotEnoughWorkersException extends Exception {
    public NotEnoughWorkersException(String message) {
        super(message);
    }
}