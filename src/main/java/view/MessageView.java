package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ArrayTaskList;
import model.Task;
import model.TaskInterface;
import org.slf4j.*;

public class MessageView implements ActionList, MethodsViewInterface {

	private JPanel panel;
	private JFrame messageFrame;
	private JButton skipButton;
	private JButton closeButton;
	private JLabel taskLabel;
	private ActionListener listener;
	private static final Logger Log = LoggerFactory.getLogger(MessageView.class);

	public MessageView() {
		Log.info("Creating new window for message");
		messageFrame = new JFrame("Message");
		panel = new JPanel();
		taskLabel = new JLabel();
		onFrameCreate();
		onButtonAdd();
		messageFrame.getContentPane().add(panel);
		messageFrame.setVisible(true);
	}
	@Override
	public void addListener (ActionListener listen) {
		listener = listen;
	}
	@Override
	public void onFrameCreate() {
		Log.debug("Creating frame");
		messageFrame.setSize(300, 155);
		messageFrame.setLocation(0, 0);
		messageFrame.setLayout(new BorderLayout());
		taskLabel.setLocation(100, 70);
		panel.add(taskLabel);
	}

	@Override
	public void onButtonAdd()  {
		Log.debug("Adding buttons and listers to them");
		skipButton = new JButton("Skip");
		skipButton.setSize(80, 30);
		skipButton.setLocation(30, 80);
		closeButton = new JButton("Cancel");
		closeButton.setSize(80, 30);
		closeButton.setLocation(180, 80);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ActionEvent ev = new ActionEvent(MessageView.this, 0, ACTION_CANCEL);
				listener.actionPerformed(ev);
			}
		});
		skipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ActionEvent ev = new ActionEvent(MessageView.this, 0, ACTION_SKIP);
				listener.actionPerformed(ev);
			}
		});
		skipButton.setLayout(null);
		closeButton.setLayout(null);
		messageFrame.add(skipButton);
		messageFrame.add(closeButton);
	}
	@Override
	public void setTaskToEdit(TaskInterface task) {
		Log.debug("Setting task to edit: " + task.getTitle());
		taskLabel.setText(task.getTitle());
	}
	@Override
	public void close() {
		messageFrame.setVisible(false);
		messageFrame.dispose();
		Log.info("Window closed");
	}
	@Override
	public String getTaskToRemove() {
		Log.debug("Getting task name to remove: " +  taskLabel.getText());
		return taskLabel.getText();
	}

	@Override
	public String getSelectedTask() {
		return null;
	}
	@Override
	public void errorMessage(Object obj) {
	}
	@Override
	public void setTasks(ArrayTaskList tasks) {	}
	@Override
	public void addButton(JButton button) {	}
	@Override
	public void onLabelAdd() {	}
	@Override
	public void onEditAdd() {	}
	@Override
	public void timeItems() {	}
	@Override
	public String getTitleField() {	return null;}
	@Override
	public boolean getActivefield() {return false;}
	@Override
	public long getRepeatIntervalField() {return 0;}
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
}
