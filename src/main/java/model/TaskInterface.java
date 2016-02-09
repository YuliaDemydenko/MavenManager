package model;

import java.util.Date;
import java.util.Iterator;

public interface TaskInterface {
    public String getTitle();
    public void setTitle(String title);
    public boolean isActive();
    public void setActive(boolean active);
    public void setTime(Date time);
    public void setTime(Date start, Date end, long interval);
    public Date getTime();
    public Date getStartTime();
    public Date getEndTime();
    public long getRepeatInterval();
    public boolean isRepeated();
    public String toString();
    public Date nextTimeAfter(Date current);
    public Task clone() throws CloneNotSupportedException;
    public boolean equals(Object obj);
    public int hashCode();
}




