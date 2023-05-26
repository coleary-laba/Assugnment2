package Errors;

public class NotEnoughWorkers extends Exception {
    public NotEnoughWorkers(String str) {
        super(str);
    }
}