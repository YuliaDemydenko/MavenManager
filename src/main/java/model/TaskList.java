package model;

import java.util.*;
import java.io.*;
import org.apache.log4j.*;

public abstract class TaskList implements Cloneable, Iterable<Task>, Serializable {

    public abstract void add(Task tasks) throws AddTaskException;
    private static final Logger Log = Logger.getLogger(TaskList.class);
    public abstract void remove(Task tasks) throws RemoveTaskExeption;
    public abstract Task getTask(int number);
    public abstract int size();

    public boolean equals(Object list) {
        if (list == this)
            return true;
        if (!(list instanceof Task))
            return false;
        Iterator<Task> it = iterator();
        Iterator<Task> e2 = iterator();
        while(it.hasNext()) {
            Task list1 = it.next();
            Object list2 = e2.next();
            if (!(list1==null ? list2==null : list1.equals(list2)))
                return false;
        }
        return !(it.hasNext() || e2.hasNext());
    }
    public int hashCode() {
        int hashCode = 1;
        for (Task e : this)
            hashCode = 12*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }
 	public TaskList clone() throws CloneNotSupportedException  {
		TaskList cloned;
		try {
            cloned = (TaskList) super.clone();
		}
		catch (	CloneNotSupportedException  e) {
            Log.error("CloneNotSupportedException");
			throw new RuntimeException("CloneNotSupportedException");
	}
		return cloned;
	}
    public Iterator<Task> iterator() {
        return new TaskListIterator();
    }

    private class TaskListIterator implements Iterator<Task> {

        int cursor = 0;    // index of next element to return
        int last = -1; // index of last element returned; -1 if no such
        Task[] tasks = new Task[size()];
        int nextCount = 0;

        public boolean hasNext() {
            return cursor != size();
        }
        public Task next() { 
            try {
                int i = cursor;
                Task next = tasks[i];
                last = i;
                cursor = i+1;
                nextCount = 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
        public void remove() {
        if ((last < 0) || (nextCount == 0))
            throw new IllegalStateException();
        try {
            TaskList.this.remove(tasks[last]);
            if (last < cursor)
                cursor--;
            last = -1;
        }
        catch (RemoveTaskExeption removeTaskExeption) {
            removeTaskExeption.printStackTrace();
        }
        catch (IndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
            nextCount = 0;
        }
    }
}