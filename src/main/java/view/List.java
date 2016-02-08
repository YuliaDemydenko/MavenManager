package view;

import model.ArrayTaskList;
import model.Task;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Date;

public interface List {
    public void addListener (ActionListener listen);
    public void onFrameCreate ();

    void setTasks(ArrayTaskList tasks);

    void addButton(JButton button);

    void onButtonAdd();
    public void close();
    public void onLabelAdd();
    public void onEditAdd();
    public void timeItems ();
    public String getTitleField ();
    public boolean getActivefield();
    public Date getStartTimeField();
    public Date getEndTimeField();
    public long getRepeatIntervalField();
    public void setTitleToEdit(String title);
    public void setActiveToEdit(boolean act);
    public void setStartTimeToEdit(int day, int month, int year, int hours, int minute);
    public void setEndTimeToEdit(int day, int month, int year, int hours, int minute);
    public void setRepeatInterval(long repeat);
    public Task getTask();
    public void setTaskToEdit(Task task);
    public String getTaskToRemove();

    String getSelectedTask();

    void errorMessage(Object obj);


}