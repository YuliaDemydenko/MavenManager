package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.log4j.*;
import model.Task;

public class MessageView implements ActionList {

	private JPanel panel;
	private JFrame messageFrame;
	private JButton skipButton;
	private JButton closeButton;
	private JLabel taskLabel;
	private ActionListener listener;
	private static final Logger Log = Logger.getLogger(MessageView.class);

	public MessageView() {
		Log.debug("Creating new window for message");
		messageFrame = new JFrame("Message");
		panel = new JPanel();
		taskLabel = new JLabel();
		frameCreate();
		buttonsAdd();
		messageFrame.getContentPane().add(panel);
		messageFrame.setVisible(true);
	}

	private void frameCreate() {
		Log.debug("Creating frame");
		messageFrame.setSize(300, 155);
		messageFrame.setLocation(0, 0);
		messageFrame.setLayout(new BorderLayout());
		taskLabel.setLocation(100, 70);
		panel.add(taskLabel);	
	}

	public void addListener (ActionListener listen) {
		listener = listen;
	}

	private void buttonsAdd() {
		Log.debug("Adding buttons and listers to them");
		skipButton = new JButton("Skip");
		skipButton.setSize(80, 30);
		skipButton.setLocation(30, 80);
		closeButton = new JButton("Cancel");
		closeButton.setSize(80, 30);
		closeButton.setLocation(180, 80);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				ActionEvent ev = new ActionEvent(MessageView.this, 0, ACTION_CANCEL);
	            listener.actionPerformed(ev);
			}
		});
		skipButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				ActionEvent ev = new ActionEvent(MessageView.this, 0, ACTION_SKIP);
	            listener.actionPerformed(ev);		
		}
		});
		skipButton.setLayout(null);
		closeButton.setLayout(null);
		messageFrame.add(skipButton);
		messageFrame.add(closeButton);
	}

	public void addMessageLabel(Task task) {
		taskLabel.setText(task.getTitle());
	}

	public void close() {
		messageFrame.setVisible(false);
		messageFrame.dispose();
		Log.info("Window closed");
	}

	public String getTaskToRemove() {
		Log.debug("Getting task name to remove");
		return taskLabel.getText();
	}

}
