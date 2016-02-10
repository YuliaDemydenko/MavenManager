package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import model.ArrayTaskList;
import model.Task;
import javax.swing.*;
import model.TaskInterface;
import org.slf4j.*;

public class MainView implements ActionList, MethodsViewInterface {

	private JFrame frame;
	private JPanel panel;
	private Checkbox button;
	private JButton JAddButton;
	private JButton JDropButton;
	private JButton UpdateButton;
	private JButton JFilterButton;
	private JButton JResetButton;
	private ActionListener listener;
	private CheckboxGroup delivery = new CheckboxGroup();
	private static final Logger Log = LoggerFactory.getLogger(MainView.class);

	public MainView() {
		Log.info("Starting constructor GUI");
		frame = new JFrame("Task Manager");
		panel = new JPanel();
		JFilterButton = new JButton("Set filter");
		JResetButton = new JButton("Clear filter");
		JAddButton = new JButton ("Add", new ImageIcon("src/main/resources/img/zakl.png"));
		JDropButton = new JButton("Delete", new ImageIcon("src/main/resources/img/delete.png"));
		UpdateButton = new JButton("Update", new ImageIcon("src/main/resources/img/edit.png"));
		onFrameCreate();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent event) {
				Log.info("Window has been closed");
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
			}

		});
		onButtonAdd();
		frame.getContentPane();
		frame.setVisible(true);
	}
	@Override
	public void addListener (ActionListener listen) {
		listener = listen;
	}
	@Override
	public void onFrameCreate () {
		Log.info("Creating Main Frame");
		frame.setSize(420, 330);
		frame.setLocation(400, 100);
		frame.setLayout(new BorderLayout());
	}
	@Override
	public void setTasks(ArrayTaskList tasks) {
		Log.info("Adding task into the Main Frame");
		panel.setBorder(BorderFactory.createTitledBorder("Tasks"));
		panel.setBackground(Color.WHITE);
		panel.setSize(500, 500);
		panel.setLayout(null);
		panel.setAutoscrolls(true);
		panel.removeAll();
		int y = 30;
		for (Task task:tasks){
			button  = new Checkbox(task.getTitle(), delivery, false);
			button.setBounds(40, y, 150, 23);
			buttonMap.put(task.getTitle(), button);
			panel.add(button);
			y+=23;
		}
		panel.repaint();
		panel.validate();
		frame.getContentPane().add(panel);

	}
	@Override
	public void addButton(JButton button) {
		Log.info("Adding buttons into the Main Frame" + button);
		frame.add(button);
	}
	@Override
	public void onButtonAdd() {
		Log.debug("Adding buttons on the frame and listeners to them");
		JAddButton.setSize(120, 40);
		JAddButton.setLocation(250, 30);
		addButton(JAddButton);
		JDropButton.setSize(120, 40);
		JDropButton.setLocation(250, 90);
		addButton(JDropButton);
		UpdateButton.setSize(120, 40);
		UpdateButton.setLocation(250, 150);
		addButton(UpdateButton);
		JFilterButton.setSize(120, 20);
		JFilterButton.setLocation(250, 210);
		addButton(JFilterButton);
		JResetButton.setSize(120, 20);
		JResetButton.setLocation(250, 250);
		addButton(JResetButton);
		JAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Log.debug("Creating listener for Add button");
				ActionEvent ev = new ActionEvent(MainView.this, 0, ACTION_ADD);
				listener.actionPerformed(ev);
				Log.info("Listener for Add button has been created");
			}
		});
		JDropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Log.debug("Creating listener for Drop button");
				ActionEvent ev = new ActionEvent(MainView.this, 0, ACTION_DROP);
				listener.actionPerformed(ev);
				Log.info("Listener for Drop button has been created");
			}
		});
		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Log.debug("Creating listener for Update button");
				ActionEvent ev = new ActionEvent(MainView.this, 0, ACTION_EDIT);
				listener.actionPerformed(ev);
				Log.info("Listener for Update button has been created");
			}
		});
		JFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Log.debug("Creating listener for FILTER button");
				ActionEvent ev = new ActionEvent(MainView.this, 0, ACTION_FILTER);
				listener.actionPerformed(ev);
				Log.info("Listener for FILTER button has been created");
			}
		});
		JResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Log.debug("Creating listener for FILTER button");
				ActionEvent ev = new ActionEvent(MainView.this, 0, ACTION_CLEAN);
				listener.actionPerformed(ev);
				Log.info("Listener for FILTER button has been created");
			}
		});
		frame.add(JAddButton);
		frame.add(JDropButton);
		frame.add(UpdateButton);
		frame.add(JFilterButton);
		frame.add(JResetButton);
		frame.add(panel);
	}
	@Override
	public String getSelectedTask() {
		Log.info("Select task from list");
		Checkbox box = delivery.getSelectedCheckbox();
		if (box !=null) {
			Log.debug("Selected task: ",box.getLabel());
			return box.getLabel();
		} else {
			return null;
		}

	}
	@Override
	public void errorMessage(Object obj) {
		Log.error("Task wasn`t selected");
		JOptionPane.showMessageDialog(null, obj, "Information", JOptionPane.PLAIN_MESSAGE);
	}
	@Override
	public void close() {}
	@Override
	public void onLabelAdd() {}
	@Override
	public void onEditAdd() {}
	@Override
	public void timeItems() {}
	@Override
	public String getTitleField() {	return null;}
	@Override
	public boolean getActivefield() {return false;}
	@Override
	public long getRepeatIntervalField() {	return 0;	}
	@Override
	public void setTitleToEdit(String title) {}
	@Override
	public void setActiveToEdit(boolean act) {}
	@Override
	public void setStartTimeToEdit(int day, int month, int year, int hours, int minute) {}
	@Override
	public void setEndTimeToEdit(int day, int month, int year, int hours, int minute) {}
	@Override
	public void setRepeatInterval(long repeat) {}
	@Override
	public Task getTask() {	return null;}
	@Override
	public void setTaskToEdit(TaskInterface task) {}
	@Override
	public String getTaskToRemove() {return null;}
}
