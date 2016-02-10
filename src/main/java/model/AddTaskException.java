package model;

import java.io.IOException;

public class AddTaskException extends Exception {

    public AddTaskException(String message) {
        super(message);
    }

    public AddTaskException(String message, Throwable cause) {
        super(message, cause);
    }
}

