package model;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public interface ManagerInterface extends IObservable {
    public void notify(Object obj);
    public void addTask(TaskInterface task) throws AddTaskException;
    public ArrayTaskList getTaskList();
    public void textReader(TaskList tasks, File fileName) throws IOException, ParseException, AddTaskException;
    public void textWriter(TaskList tasks, File fileName) throws IOException;
    public void removeTask(TaskInterface task) throws RemoveTaskExeption;
    public Task getTask(String title) throws TaskNotFoundExeption;
    public void readTaskList(ArrayTaskList tasks) throws AddTaskException;
}
