package model;

import java.util.Iterator;

public interface ArrayTaskListInterface {
    public void addTask(TaskInterface task) throws AddTaskException;
    public void removeTask(TaskInterface task) throws RemoveTaskExeption;
    public TaskInterface getTask(int number) throws GetTaskExeption;
    public int size();
    public TaskList clone() throws CloneNotSupportedException;
    public boolean equals(Object list);
    public int hashCode();
    public String toString();
    public Iterator<Task> iterator();
}
