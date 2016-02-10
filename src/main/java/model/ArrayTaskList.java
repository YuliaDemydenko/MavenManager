package model;

import java.util.*;
import org.slf4j.*;

public class ArrayTaskList extends TaskList implements ArrayTaskListInterface {
    public TaskInterface[] task_list = new Task[10];
    private static final Logger Log = LoggerFactory.getLogger(ArrayTaskList.class);
    private int size;

    @Override
    public void addTask(TaskInterface task) throws AddTaskException {
        if (task == null) {
            Log.error("Wasn`t choose task for edit. AddTaskExeption");
            throw new AddTaskException("Error! Wasn`t choose task for edit!");
        }
        if (size() >= task_list.length) {
            TaskInterface[] buff = new Task[task_list.length*2];
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
    public void removeTask(TaskInterface task) throws RemoveTaskExeption{
		if (task == null) {
            Log.error("Task for remove hasn`t been selected. RemoveTaskExeption");
            throw new RemoveTaskExeption("Error! Task for remove hasn`t been selected");
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
    public TaskInterface getTask(int number) throws GetTaskExeption {
        if ( number < 0 || number > size()-1) {
            Log.error("GetTaskExeption. Number of task is nonexist" + number);
            throw new GetTaskExeption("Nonexistent task with number:", number);
        } else {
            Log.debug("Getting task with number: " + number);
            return task_list[number];
        }
    }
    @Override
    public int size() {
        Log.debug("Returning size of task array: " + this.size);
        return this.size;
    }
    @Override
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
          throw new EmptyTaskExeption("Such element doesn`t exist", last);
          Log.error("IllegalStateException");
            try {
                ArrayTaskList.this.removeTask(task_list[last]);
                cursor = last;
                last = -1;
            }
            catch (RemoveTaskExeption removeTaskExeption) {
                removeTaskExeption.printStackTrace();
                Log.debug("RemoveTaskExeption" + removeTaskExeption);
            }
            catch (IndexOutOfBoundsException ex) {
                Log.debug("IndexOutOfBoundsException" + ex);
                throw new ConcurrentModificationException();
            }
      }
    }
}