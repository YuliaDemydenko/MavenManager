package model;

import java.util.Date;
import java.util.Iterator;

public interface TaskInterface {
    public void addTask(Task task) throws AddTaskException;
    public void removeTask(Task task) throws RemoveTaskExeption;
    public Task getTask(String title);
    public Task getTask(int number);
    public int size();
    public boolean equals(Object list);
    public int hashCode();
    public String toString();
    public Iterator<Task> iterator();
    public void notify(Object obj);
    public ArrayTaskList getTaskList();
    public void readTaskList(ArrayTaskList tasks);
    public String getTitle();
    public boolean isActive();
    public void setActive(boolean active);
    public void setTime(Date time);
    public void setTime(Date start, Date end, long interval);
    public Date getTime();
    public Date getStartTime();
    public Date getEndTime();
    public long getRepeatInterval();
    public boolean isRepeated();
    public Date nextTimeAfter(Date current);
    public void setTitle(String title);
}




