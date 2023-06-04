package exceptions;

public class ToManyWorkersException extends Exception {
    public ToManyWorkersException(String message) {
        super(message);
    }
}
