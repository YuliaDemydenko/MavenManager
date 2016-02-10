package model;

/**
 * Created by Julia on 09.02.2016.
 */
public class EmptyTaskExeption extends RuntimeException {

    public EmptyTaskExeption(String message) {
        super(message);
    }
    public EmptyTaskExeption(String message, Throwable cause) {
        super(message, cause);
    }
    public EmptyTaskExeption(String message, int last) {
    }
}
