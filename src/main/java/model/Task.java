package model;

import java.util.*;
import java.io.*;
import org.slf4j.*;

public class Task implements TaskInterface, Cloneable, Serializable {
    private String title;
    private Date time;
    private Date start;
    private Date end;
    private long interval;
    private boolean active;
    private static final Logger Log = LoggerFactory.getLogger(Task.class);

    public Task(String title, Date time) throws AddTaskException{
        Log.debug ("Setting title for task");
        if (title == null || time.getTime() < 0) {
            Log.error("AddTaskExeption. Task title is empty/Task Time is incorrect" + title + time.getTime());
            throw new AddTaskException("Task title is empty/Task Time is incorrect" + title + time.getTime());
        }
        this.title = title;
        Log.debug("Setting new title to the task: " + this.title);
        setTime(time);
        this.active = false;
    }
    public Task(String title, Date start, Date end, long interval) throws AddTaskException{
        Log.debug ("Creating new task");
        if (title == null || start.getTime()< 0 || end.getTime() < 0|| interval < 0) {
            Log.error("AddTaskExeption. Task title is empty/Task Time is incorrect" + title + time.getTime());
            throw new AddTaskException("Task title is empty/Task Time is incorrect" + title + time.getTime());
        } else {
            this.title = title;
            Log.debug("Setting title to the task" + this.title);
            setTime(start, end, interval);
            this.active = false;
            Log.info("New task created");
        }
    }
    @Override
    public String getTitle() {
        Log.debug("Returning title of the task" + title);
        return title;
    }
    @Override
    public void setTitle(String title) throws EmptyTaskExeption{
        Log.debug ("Set title for task");
		if (title ==  null) {
            Log.error("Title is empty. EmptyTaskExeption");
			throw new EmptyTaskExeption("Title is empty!");
		} else {
			this.title = title;
            Log.debug("Title for task has been created: " + title);
		}
	}
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public void setActive(boolean active){
		this.active = active;
    }
    @Override
    public void setTime(Date time) throws EmptyTaskExeption{
        if (time.getTime() < 0) {
            Log.error("Incorrect time for task: " + time.getTime());
            throw new EmptyTaskExeption("Incorrect time for task");
        } else {
            this.time = time;
            this.interval = 0;
            Log.debug("Time for task has been set" + time);
        }
    }
    @Override
    public void setTime(Date start, Date end, long interval) throws EmptyTaskExeption{
        Log.debug ("Set start, end time and interval for task");
        if (start.getTime() < 0 || interval < 0) {
            Log.error("IllegalArgumentException");
            throw new EmptyTaskExeption("Incorrect start time/interval for task" + start.getTime() + interval);
        } else {
            this.start = start;
            this.end = end;
            this.interval = interval;
            Log.debug("Start " + start + " end " + end + " time and interval "+ interval + " for task has been set");
        }
    }
    @Override
    public Date getTime() {
        Log.info("Get time for task");
        if (isRepeated()) {
            Log.debug("Return start time: " + this.start);
            return this.start;
        } else {
            Log.debug("Return new time", this.time);
            return this.time;
        }
    }
    @Override
    public Date getStartTime() {
        Log.info("Get start time for task");
        if (isRepeated()) {
            Log.debug("Return start time: " + start);
            return start;
        }
        else {
            Log.debug("Return new time", time);
            return time; }
    }
    @Override
    public Date getEndTime(){
        Log.info("Get end time for task");
		if (isRepeated()) {
            Log.debug("Return end time: " + this.end);
            return this.end;
		} else {
            Log.debug("Return new time", this.time);
			return this.time;
		}
    }
    @Override
    public long getRepeatInterval(){
        Log.info("Get interval for task");
        if (isRepeated()) {
            Log.debug("Get previous interval, if repeated: " + this.interval);
            return this.interval;
        } else {
            Log.info("Task is not repeated");
            return 0;
        }
    }
    @Override
    public boolean isRepeated() {
        if (interval > 0)
            return true;
        else
            return false;
    }
    @Override
    public String toString() {
        if (isRepeated()) {
            return ("Task: " + title + " from " + start + " to " + end + " and every " + interval);
        } else {
            return ("Task: " + title + " at " + start);
        }
    }
    @Override
    public Date nextTimeAfter(Date current) {
        if (isActive()) {
            if ((!isRepeated()) && (current.compareTo(time)<=0)) {
                return time;
            } else if ((isRepeated())  && (current.compareTo(new Date(end.getTime()-interval))<0)) {
                Date next = null;
                if (current.compareTo(start)<0) {
                    next = start;
                }
                else {
                    for (long i = start.getTime(); i <= current.getTime(); i += interval) {
                        next = new Date(i + interval);
                    }
                }
                return next;
            }
            else return null;
        }
        else return null;
    }

    public Task clone() throws CloneNotSupportedException {
            Task cloned = (Task) super.clone();
            return cloned;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return time == task.time &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                active == task.active &&
                Objects.equals(title, task.title);
    }
    @Override
    public int hashCode() {
        int hash = 1;
        hash *= 10 + getTitle().hashCode();
        if (isActive()) {
            hash += 2;
        } else
            hash += 0;
        if (isRepeated()) {
            hash *= this.start.hashCode() + this.end.hashCode() + this.interval;
        } else
            hash += 0;
        return hash;
    }
}

