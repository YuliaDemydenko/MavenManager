package view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ArrayTaskList;
import model.Task;
import org.slf4j.*;

public class AddView  implements ActionList, List{

	private JFrame frame;
	private JLabel JNameLabel;
	private JLabel JActiveLabel;
	private JLabel JStartLabel;
	private JLabel JEndLabel;
	private JLabel JIntLabel;
	private JButton saveButton;
	private JButton cancelButton;
	private JPanel panel;
	private TextField titleEdit = new TextField();
	private Choice activeEdit;
	private Choice StartDate;
	private Choice StartMonth;
	private Choice StartYear;
	private Choice StartMinute;
	private Choice StartHour;
	private static Choice EndDate;
	private static Choice EndMonth;
	private static Choice EndYear;
	private static Choice EndMinute;
	private static Choice EndHour;
	private Choice Interval;
	private ActionListener listener;
	private static final Logger Log = LoggerFactory.getLogger(AddView.class);

	public AddView(String title) {
		Log.debug("Creating new window");
		frame = new JFrame(title);
		panel = new JPanel();
		onFrameCreate();
		onEditAdd();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		onLabelAdd();
		timeItems();
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		Log.info("New window was created");
	}
	@Override
	public void addListener (ActionListener listen) {
		Log.debug("Adding listener");
		listener = listen;
	}

	@Override
	public void onFrameCreate() {
		Log.debug("Creating new frame for add");
		frame.setSize(420, 300);
		frame.setLocation(400, 100);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		JNameLabel = new JLabel("Task's title");
		JActiveLabel = new JLabel("Task's activity");
		JStartLabel = new JLabel("Start time");
		JEndLabel = new JLabel("End time");
		JIntLabel = new JLabel("Repeat interval");
		Log.info("New frame has been created");
	}
	@Override
	public void setTasks(ArrayTaskList tasks) {	}
	@Override
	public void addButton(JButton button) {	}

	@Override
	public void onButtonAdd() {
		Log.debug("Creating buttons for add frame");
		saveButton = new JButton("Save");
		saveButton.setSize(80, 30);
		saveButton.setLocation(80, 220);
		cancelButton = new JButton("Cancel");
		cancelButton.setSize(80, 30);
		cancelButton.setLocation(260, 220);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Log.debug("Creating CLOSE button");
				ActionEvent ev = new ActionEvent(AddView.this, 0, ACTION_CLOSE);
				listener.actionPerformed(ev);
			}
		});
		saveButton.setLayout(null);
		cancelButton.setLayout(null);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Log.debug("Creating SAVE button");
				ActionEvent ev = new ActionEvent(AddView.this, 0, ACTION_SAVE);
				listener.actionPerformed(ev);
			}
		});
		frame.add(saveButton);
		frame.add(cancelButton);
		Log.debug("Buttons has been created");
	}
	@Override
	public void onLabelAdd() {
		Log.debug("Adding label on add panel");
		JNameLabel.setBounds(10, 10, 100, 20);
		JActiveLabel.setBounds(10, 40, 100, 20);
		JStartLabel.setBounds(10, 70, 100, 20);
		JEndLabel.setBounds(10, 100, 100, 20);
		JIntLabel.setBounds(10, 130, 100, 20);
		panel.add(JActiveLabel);
		panel.add(JNameLabel);
		panel.add(JStartLabel);
		panel.add(JEndLabel);
		panel.add(JIntLabel);
	}
	@Override
	public void onEditAdd() {
		titleEdit.setBounds(110, 10, 150, 20);
		panel.add(titleEdit);
		activeEdit = new Choice();
		activeEdit.setBounds(110, 40, 150, 20);
		activeEdit.addItem("ON");
		activeEdit.addItem("OFF");
		panel.add(activeEdit);
		StartYear = new Choice();
		StartMonth = new Choice();
		StartDate = new Choice();
		StartMinute = new Choice();
		StartHour = new Choice();
		StartDate.setBounds(110, 70, 50, 20);
		StartMonth.setBounds(160, 70, 50, 20);
		StartYear.setBounds(210, 70, 80, 20);
		StartHour.setBounds(300, 70, 50, 20);
		StartMinute.setBounds(350, 70, 50, 20);
		panel.add(StartYear);
		panel.add(StartMonth);
		panel.add(StartDate);
		panel.add(StartHour);
		panel.add(StartMinute);
		EndYear = new Choice();
		EndMonth = new Choice();
		EndDate = new Choice();
		EndMinute = new Choice();
		EndHour = new Choice();
		EndDate.setBounds(110, 100, 50, 20);
		EndMonth.setBounds(160, 100, 50, 20);
		EndYear.setBounds(210, 100, 80, 20);
		EndHour.setBounds(300, 100, 50, 20);
		EndMinute.setBounds(350, 100, 50, 20);
		panel.add(EndYear);
		panel.add(EndMonth);
		panel.add(EndDate);
		panel.add(EndHour);
		panel.add(EndMinute);
		Interval=new Choice();
		Interval.setBounds(110, 130, 150, 20);
		panel.add(Interval);
	}
	@Override
	public void close() {
		frame.setVisible(false);
		Log.info("Window closed");
	}
	@Override
	public void timeItems () {
		Log.debug("Adding items for date");
		for (int i =1; i<=31;i++){
			if (i<10) {
				StartDate.addItem("0"+i+"");
				EndDate.addItem("0"+i+"");
			} else {
				StartDate.addItem(""+i+"");
				EndDate.addItem(""+i+"");
			}
		}
		for (int i = 1;i<=12;i++){
			if (i<10){
				StartMonth.addItem("0"+i+"");
				EndMonth.addItem("0"+i+"");
			} else {
				StartMonth.addItem(""+i+"");
				EndMonth.addItem(""+i+"");
			}
		}
		for (int i = 2016; i<=2100; i++) {
			StartYear.addItem(""+i);
			EndYear.addItem(""+i);
		}
		for (int i = 0; i<24; i++) {
			if (i<10) {
				StartHour.addItem("0"+i);
				EndHour.addItem("0"+i);
			} else {
				StartHour.addItem(""+i);
				EndHour.addItem(""+i);
			}
		}
		for (int i = 0; i<60;i++) {
			if (i<10) {
				StartMinute.addItem("0"+i);
				EndMinute.addItem("0"+i);
			} else {
				StartMinute.addItem(""+i);
				EndMinute.addItem(""+i);
			}
		}
		Interval.addItem("Every minute");
		Interval.addItem("Every day");
		Interval.addItem("Every month");
		Interval.addItem("Every year");
		Log.info("Items created");
	}
	@Override
	public String getTitleField () {
		Log.debug("Returning text from title Edit");
		return titleEdit.getText();
	}
	@Override
	public boolean getActivefield() {
		Log.debug("Returning the activity of task");
		int taskActive =activeEdit.getSelectedIndex();
		if (taskActive == 0) {
			return true;
		} else {
			return false;
		}
	}
	public Date getStartTimeField() {
		Log.debug("Return start time of task");
		int startDate = Integer.parseInt(StartDate.getSelectedItem());
		int startMonth = Integer.parseInt(StartMonth.getSelectedItem());
		int startYear = Integer.parseInt(StartYear.getSelectedItem());
		int startHour = Integer.parseInt(StartHour.getSelectedItem());
		int startMinute = Integer.parseInt(StartMinute.getSelectedItem());
		Calendar startTime = Calendar.getInstance();
		startTime.set(startYear, startMonth - 1, startDate, startHour, startMinute, 0);
		return startTime.getTime();
	}
	public static Date getEndTimeField() {
		Log.debug("Return end time of task");
		int endDate = Integer.parseInt(EndDate.getSelectedItem());
		int endMonth = Integer.parseInt(EndMonth.getSelectedItem());
		int endYear = Integer.parseInt(EndYear.getSelectedItem());
		int endHour = Integer.parseInt(EndHour.getSelectedItem());
		int endMinute = Integer.parseInt(EndMinute.getSelectedItem());
		Calendar endTime = Calendar.getInstance();
		endTime.set(endYear,endMonth-1, endDate, endHour, endMinute,0);
		return endTime.getTime();
	}
	@Override
	public long getRepeatIntervalField() {
		Log.debug("Return interval of task");
		String repeat = Interval.getSelectedItem();
		if (repeat.equals("Every minute")) {
			return 1*60*1000;
		}
		else
		if (repeat.equals("Every day")) {
			return 24*60*60*1000;
		}
		else
		if (repeat.equals("Every month") ) {
			return 31*24*60*60*1000;
		}
		else
			return 365*24*60*60*1000;
	}
	@Override
	public void setTitleToEdit(String title) {
		Log.debug("Setting title of update task");
		titleEdit.setText(title);
	}
	/**
	 * This method selects a proper item in activeEdit if act = true select 0 else 1
	 * @param act
	 */
	@Override
	public void setActiveToEdit(boolean act) {
		Log.debug("Setting activity for task");
		if (act) {
			activeEdit.select(0);
		} else {
			activeEdit.select(1);
		}
	}
	@Override
	public void setStartTimeToEdit(int day, int month, int year, int hours, int minute) {
		Log.debug("Selecting date on the start edits");
		Calendar calendar = Calendar.getInstance();
		StartDate.select(day-1);
		Log.info("Set day");
		StartMonth.select(month);
		StartYear.select(year-calendar.get(Calendar.YEAR));
		StartHour.select(hours);
		StartMinute.select(minute);
	}
	@Override
	public void setEndTimeToEdit(int day, int month, int year, int hours, int minute) {
		Log.debug("Selecting date on the end edits");
		Calendar calendar = Calendar.getInstance();
		EndDate.select(day-1);
		EndMonth.select(month);
		EndYear.select(year-calendar.get(Calendar.YEAR));
		EndHour.select(hours);
		EndMinute.select(minute);

	}
	@Override
	public void setRepeatInterval(long repeat) {
		Log.debug("Selecting items for repeat Edit");
		if (repeat<24*60*60*1000) {
			Interval.select(0);
		} else if (repeat<31*24*60*60*1000) {
			Interval.select(1);
		} else if (repeat<365*24*60*60*1000) {
			Interval.select(2);
		} else {
			Interval.select(3);
		}
	}
	@Override
	public Task getTask() {
		Log.debug("Creating new task from the editing fields");
		Task task;
		if (getActivefield()){
			task = new Task(getTitleField(),new Date(String.valueOf(getStartTimeField())), new Date(String.valueOf(getEndTimeField())), getRepeatIntervalField());
			task.setActive(getActivefield());
		} else {
			task = new Task(getTitleField(),new Date(String.valueOf(getStartTimeField())));
		}
		return task;
	}
	@Override
	public void setTaskToEdit(Task task) {
		Log.debug("Setting chosen task to edit");
		setTitleToEdit(task.getTitle());
		setActiveToEdit(task.isActive());
		setStartTimeToEdit(getDay(task.getStartTime()), getMonth(task.getStartTime()), getYear(task.getStartTime()), getHour(task.getStartTime()), getMinute(task.getStartTime()));
		setEndTimeToEdit(getDay(task.getEndTime()), getMonth(task.getEndTime()), getYear(task.getEndTime()), getHour(task.getEndTime()), getMinute(task.getEndTime()));
		setRepeatInterval(task.getRepeatInterval());
	}

	@Override
	public String getTaskToRemove() {
		return null;
	}

	@Override
	public String getSelectedTask() {
		return null;
	}

	@Override
	public void errorMessage(Object obj) {

	}

	public int getDay(Date data) {
		Log.debug("Gets day from data");
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.DATE);
	}
	public int getMonth(Date data) {
		Log.debug("Gets month from data");
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.MONTH);
	}
	public int getYear(Date data) {
		Log.debug("Gets year from data");
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.YEAR);
	}
	public int getHour(Date data) {
		Log.debug("Gets hours from data");
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute(Date data) {
		Log.debug("Gets minutes from data");
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.MINUTE);
	}
}
