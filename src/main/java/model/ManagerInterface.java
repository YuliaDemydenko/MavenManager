package model;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public interface ManagerInterface extends IObserveble{
    public void notify(Object obj);
    public void addTask(Task task) throws AddTaskException;
    public ArrayTaskList getTaskList();
    public void textReader(TaskList tasks, File fileName) throws IOException, ParseException;
    public void textWriter(TaskList tasks, File fileName) throws IOException;
    public void removeTask(TaskInterface task) throws RemoveTaskExeption;
    public Task getTask(String title);
    public void readTaskList(ArrayTaskList tasks);
}
