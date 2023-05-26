package Errors;

public class ToManyWorkers extends Exception {
    public ToManyWorkers(String str) {
        super(str);
    }
}
