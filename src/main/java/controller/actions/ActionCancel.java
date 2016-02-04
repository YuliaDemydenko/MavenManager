package controller.actions;

import model.ManagerModel;
import model.Task;
import org.apache.log4j.Logger;
import view.MainView;
import view.MessageView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionCancel implements Action {
	private static final Logger Log = Logger.getLogger(ActionCancel.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
			Log.debug("An action 'CANCEL' occurs");
			if (source instanceof MessageView) {
				((MessageView) source).close();
				Log.info("Message view was closed");
			}
			try {
				for (Task task : model.getTaskList()) {
					if (task.getTitle().equals(((MessageView) source).getTaskToRemove())) {
						Log.debug("Removing task from frame");
						model.removeTask(task);
					}
				}
				Log.info("Successful 'CANCEL' action");
			}
			catch (IOException e) {
				view.errorMessage(e);
				Log.error("IOException error");
			}
		}
	}

