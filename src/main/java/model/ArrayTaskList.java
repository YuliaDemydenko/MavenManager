package model;

import java.util.*;
import org.apache.log4j.*;

public class ArrayTaskList extends TaskList implements TaskInterface{
    public Task[] task_list = new Task[10];
    private static final Logger Log = Logger.getLogger(ArrayTaskList.class);
    private int size;

    @Override
    public void addTask(Task task) throws AddTaskException {
        Log.debug("Creating new task");
        if (task == null) {
            Log.error("Task is empty. NullPointerException");
            throw new AddTaskException("Task is empty!");
        }
        if (size() >= task_list.length) {
            Task[] buff = new Task[task_list.length*2];
            for(int i = 0; i < size(); i++) {
                buff[i] = this.task_list[i];
            }
            this.task_list = buff;
            this.task_list[size()] = task;
        } else {
            this.task_list[size()] = task;
        }
        this.size ++;
        Log.info("Task has been added into the frame");
    }


    @Override
    public void removeTask(Task task) throws RemoveTaskExeption { }
    @Override
    public Task getTask(String title) {   return null;  }
    @Override
    public void remove(Task task) throws RemoveTaskExeption{
        Log.debug("Removing task");
		if (task == null) {
            Log.error("Task hasn`t been selected.");
            throw new RemoveTaskExeption("Task hasn`t been selected");
        }
        int number = 0;
		for (int i = 0; i < size(); i++) {
            if (this.task_list[i].equals(task)) {
                number = 1;
                for (int j = i; j< size(); j++) {
                    task_list[j] = task_list[j+1];
                }
                this.size--;
            }   
        }
        Log.info("Task has been deleted");
   }
    @Override
    public Task getTask(int number) {
        Log.info("Something happenes");
        if ( number < 0 || number > size()-1) {
            Log.error("Something happened. IllegalArgumentException");
            throw new IllegalArgumentException();
        } else {
            return task_list[number];
        }
    }
    @Override
    public int size() {
        return this.size;
    }
    public TaskList clone() throws CloneNotSupportedException {
        ArrayTaskList cloned = (ArrayTaskList) super.clone();
        cloned.task_list = (Task[]) task_list.clone(); 
        return cloned;
    }
    @Override
    public boolean equals(Object list) {
		if(list == null) {
			return false;
		}
        if (list == this) {
            return true;
        }
		if (list.getClass() == this.getClass() ) {
            ArrayTaskList nlist = (ArrayTaskList) list;
			for (int i = 0; i < this.size(); i++) {
				if (!this.task_list[i].equals(nlist.task_list[i]))
					return false;
			}
            return true;
		}
        return false;
	}
    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Task e : this)
            hashCode = 12*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }
    @Override
    public String toString() {
        Iterator<Task> it = iterator();
        if (! it.hasNext())
            return "[]";
        String string ="ArrayTaskList: ";
        while (it.hasNext()) {
            string += "[" + it.next().toString() + "] ";
        }
        return string;
    }
    @Override
   public Iterator<Task> iterator() {
        Iterator<Task> it = new ArrayListIterator();
        return it;
    }

    @Override
    public void notify(Object obj) {}

    @Override
    public ArrayTaskList getTaskList() { return null;  }
    @Override
    public void readTaskList(ArrayTaskList tasks) {}
    @Override
    public String getTitle() {
        return null;
    }
    @Override
    public boolean isActive() {
        return false;
    }
    @Override
    public void setActive(boolean active) {}
    @Override
    public void setTime(Date time) {}
    @Override
    public void setTime(Date start, Date end, long interval) {}
    @Override
    public Date getTime() {
        return null;
    }
    @Override
    public Date getStartTime() {
        return null;
    }
    @Override
    public Date getEndTime() {
        return null;
    }
    @Override
    public long getRepeatInterval() {
        return 0;
    }
    @Override
    public boolean isRepeated() {
        return false;
    }
    @Override
    public Date nextTimeAfter(Date current) {
        return null;
    }
    @Override
    public void setTitle(String title) {}

    class ArrayListIterator implements Iterator<Task> {
        private int cursor;
        private int last = -1;

        public boolean hasNext() {
           return cursor != size();
        }

        public Task next() {
            int i = cursor;
            if (i>= size())
            i=size()-1;
            Object[] task_list = ArrayTaskList.this.task_list;
            if (i >= task_list.length)
                throw new ConcurrentModificationException();
            cursor = i+1;
            return (Task) task_list[last = i];
            }
      public void remove() {
          Log.debug("Removing the last element returned by the iterator");
        	if(last <0 )
          throw new IllegalStateException();
          Log.error("IllegalStateException");
            try {
                ArrayTaskList.this.remove(task_list[last]);
                cursor = last;
                last = -1;
            } catch (RemoveTaskExeption removeTaskExeption) {
                removeTaskExeption.printStackTrace();
            }
            catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
      }
    }
}