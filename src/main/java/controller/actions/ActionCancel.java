package controller.actions;

import model.ManagerModel;
import model.Task;
import org.slf4j.*;
import view.MainView;
import view.MessageView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionCancel implements Action {
	private static final Logger Log = LoggerFactory.getLogger(ActionCancel.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
			if (source instanceof MessageView) {
				((MessageView) source).close();
				Log.info("Message view was closed");
			}
			try {
				for (Task task : model.getTaskList()) {
					if (task.getTitle().equals(((MessageView) source).getTaskToRemove())) {
						Log.debug("Removing task from frame", task.getTitle());
						model.removeTask(task);
					}
				}
			}
			catch (IOException e) {
				view.errorMessage(e);
				Log.error("IOException error", e);
			}
		}
	}

