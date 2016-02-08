package view;

import model.ArrayTaskList;
import model.Task;
import org.slf4j.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class ShowView  implements ActionList, List{

    private JFrame frame;
    private JLabel JStartLabel;
    private JLabel JEndLabel;
    private JButton cancelButton;
    private JButton applyButton;
    private JPanel panel;
    private static Choice StartDate;
    private static Choice StartMonth;
    private static Choice StartYear;
    private static Choice EndDate;
    private static Choice EndMonth;
    private static Choice EndYear;
    private ActionListener listener;
    private static final Logger Log = LoggerFactory.getLogger(AddView.class);

    public ShowView (String title) {
        Log.debug("Creating new window");
        frame = new JFrame(title);
        panel = new JPanel();
        onFrameCreate();
        onEditAdd();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        onLabelAdd();
        onButtonAdd();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        timeItems();
        Log.info("New window was created");
    }
    @Override
    public void addListener (ActionListener listen) {
        Log.debug("Adding listener");
        listener = listen;
    }

    @Override
    public void onFrameCreate() {
        Log.debug("Creating new frame for filter");
        frame.setSize(420, 300);
        frame.setLocation(400, 100);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        JStartLabel = new JLabel("Start time");
        JEndLabel = new JLabel("End time");

        Log.info("New frame has been created");}

    @Override
    public void onButtonAdd () {
        Log.debug("Creating buttons for filter frame and adding listeners");
        applyButton = new JButton("Apply");
        applyButton.setSize(80, 30);
        applyButton.setLocation(80, 220);
        cancelButton = new JButton("Cancel");
        cancelButton.setSize(80, 30);
        cancelButton.setLocation(260, 220);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Log.debug("Creating CLOSE button");
                ActionEvent ev = new ActionEvent(ShowView.this, 0, ACTION_CLOSEFILTER);
                listener.actionPerformed(ev);
            }
        });
        applyButton.setLayout(null);
        cancelButton.setLayout(null);

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Log.debug("Creating Apply button");
                ActionEvent ev = new ActionEvent(ShowView.this, 0, ACTION_APPLY);
                listener.actionPerformed(ev);
            }
        });
        frame.add(applyButton);
        frame.add(cancelButton);
        Log.debug("Buttons has been created");
    }
   @Override
    public void onLabelAdd() {
        Log.debug("Adding label on filter panel");
        JStartLabel.setBounds(10, 70, 100, 20);
        JEndLabel.setBounds(10, 100, 100, 20);
        panel.add(JStartLabel);
        panel.add(JEndLabel);
    }
   @Override
    public void onEditAdd() {
        Log.debug("Adding time panels on a frame");
        StartYear = new Choice();
        StartMonth = new Choice();
        StartDate = new Choice();
        StartDate.setBounds(110, 70, 50, 20);
        StartMonth.setBounds(160, 70, 50, 20);
        StartYear.setBounds(210, 70, 80, 20);
        panel.add(StartYear);
        panel.add(StartMonth);
        panel.add(StartDate);
        EndYear = new Choice();
        EndMonth = new Choice();
        EndDate = new Choice();
        EndDate.setBounds(110, 100, 50, 20);
        EndMonth.setBounds(160, 100, 50, 20);
        EndYear.setBounds(210, 100, 80, 20);
        panel.add(EndYear);
        panel.add(EndMonth);
        panel.add(EndDate);

    }
    @Override
    public void timeItems () {
        Log.debug("Adding items for dates");
        for (int i = 1; i <= 31; i++) {
            if (i < 10) {
                StartDate.addItem("0" + i + "");
                EndDate.addItem("0" + i + "");
            } else {
                StartDate.addItem("" + i + "");
                EndDate.addItem("" + i + "");
            }
        }
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                StartMonth.addItem("0" + i + "");
                EndMonth.addItem("0" + i + "");
            } else {
                StartMonth.addItem("" + i + "");
                EndMonth.addItem("" + i + "");
            }
        }
        for (int i = 2016; i <= 2100; i++) {
            StartYear.addItem("" + i);
            EndYear.addItem("" + i);
        }
    }
    @Override
    public void close() {
        frame.setVisible(false);
        Log.info("Window closed");
    }
    @Override
    public Date getStartTimeField() {
        Log.debug("Return start time of filter");
        int startDate = Integer.parseInt(StartDate.getSelectedItem());
        int startMonth = Integer.parseInt(StartMonth.getSelectedItem());
        int startYear = Integer.parseInt(StartYear.getSelectedItem());
        Calendar startTime = Calendar.getInstance();
        startTime.set(startYear, startMonth - 1, startDate);
        Date start = startTime.getTime();
        return start;
    }
    @Override
    public Date getEndTimeField() {
        Log.debug("Return end time of filter");
        int endDate = Integer.parseInt(EndDate.getSelectedItem());
        int endMonth = Integer.parseInt(EndMonth.getSelectedItem());
        int endYear = Integer.parseInt(EndYear.getSelectedItem());
        Calendar endTime = Calendar.getInstance();
        endTime.set(endYear, endMonth - 1, endDate);
        Date end = endTime.getTime();
        return end;
    }
    @Override
    public long getRepeatIntervalField() { return 0; }
    @Override
    public void setTitleToEdit(String title) { }
    @Override
    public void setActiveToEdit(boolean act) { }
    @Override
    public void setStartTimeToEdit(int day, int month, int year, int hours, int minute) { }
    @Override
    public void setEndTimeToEdit(int day, int month, int year, int hours, int minute) { }
    @Override
    public void setRepeatInterval(long repeat) {}
    @Override
    public Task getTask() { return null;   }
    @Override
    public void setTaskToEdit(Task task) {
    }
    @Override
    public String getTaskToRemove() { return null;  }
    @Override
    public String getSelectedTask() { return null;  }
    @Override
    public void errorMessage(Object obj) {}
    @Override
    public String getTitleField() {return null; }
    @Override
    public boolean getActivefield() { return false; }
    @Override
    public void setTasks(ArrayTaskList tasks) {}
    @Override
    public void addButton(JButton button) {}

}
