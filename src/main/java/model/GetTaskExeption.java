package model;

public class GetTaskExeption extends Exception{

    public GetTaskExeption(String message, int number) {
        super(message + number);
    }
}
