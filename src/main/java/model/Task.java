package model;

import java.text.ParseException;
import java.util.*;
import java.io.*;
import org.apache.log4j.*;

public class Task implements TaskInterface, Cloneable, Serializable {
    private String title;
    private Date time;
    private Date start;
    private Date end;
    private long interval;
    private boolean active;
    private static final Logger Log = Logger.getLogger(Task.class);

    public Task(String title, Date time) {
        Log.debug ("Setting title for task");
        if (title == null || time.getTime() < 0) {
            Log.error("IllegalArgumentException");
            throw new IllegalArgumentException();
        }
        this.title = title;
        setTime(time);
        this.active = false;
    }
    public Task(String title, Date start, Date end, long interval) {
        Log.debug ("Creating new task");
        if (title == null || start.getTime()< 0 || end.getTime() < 0|| interval < 0) {
            Log.error("IllegalArgumentException");
            throw new IllegalArgumentException();
        } else {
            this.title = title;
            setTime(start,end,interval);
            this.active = false;
            Log.info("New task created");
        }
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public void setTitle(String title) {
        Log.debug ("Set title for task");
		if (title ==  null) {
            Log.error("Title is empty. NullPointerException");
			throw new NullPointerException();
		} else {
			this.title = title;
            Log.info("Title for task has been created");
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
    public void setTime(Date time){
        if (time.getTime() < 0) {
            Log.error("IllegalArgumentException");
            throw new IllegalArgumentException();
        } else {
            this.time = time;
            this.interval = 0;
            Log.info("Time for task has been set");
        }
    }
    @Override
    public void setTime(Date start, Date end, long interval){
        Log.debug ("Set start, end time and interval for task");
        if (start.getTime() < 0 || interval < 0) {
            Log.error("IllegalArgumentException");
            throw new IllegalArgumentException();
        } else {
            this.start = start;
            this.end = end;
            this.interval = interval;
            Log.debug("Start, end time and interval for task has been set");
        }
    }
    @Override
    public Date getTime() {
        Log.debug("Get time for task");
        if (isRepeated()) {
            Log.info("Get the same time");
            return this.start;
        } else {
            Log.info("Get new time");
            return this.time;
        }
    }
    @Override
    public Date getStartTime() {
        Log.debug("Get start time for task");
        if (isRepeated()) {
            Log.info("Get the same time ");
            return start;
        }
        else {
            Log.info("Get new time");
            return time; }
    }
    @Override
    public Date getEndTime(){
        Log.debug("Get end time for task");
		if (isRepeated()) {
            Log.info("Get the same time ");
            return this.end;
		} else {
            Log.info("Get new time");
			return this.time;
		}
    }
    @Override
    public long getRepeatInterval(){
        Log.debug("Get interval for task");
        if (isRepeated()) {
            Log.info("Get previous interval, if repeated");
            return this.interval;
        } else {
            Log.info("Task is not repeated");
            return 0;
        }
    }
    @Override
    public boolean isRepeated() {
        if (interval > 0) return true;
        else return false;
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
        Log.debug("Tasks comparing between each other");
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

