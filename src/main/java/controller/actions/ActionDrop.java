package controller.actions;

import model.ManagerModel;
import model.Task;
import org.apache.log4j.Logger;
import view.MainView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionDrop implements Action {
	private static final Logger Log = Logger.getLogger(ActionDrop.class);
	@Override
		public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
		Log.debug("An action 'DELETE' occurs");
			if (source instanceof MainView)
				try {
					if (((MainView) source).getSelectedTask() != null) {
						Log.debug("Getting task that was selected");
						for (Task task : model.getTaskList()) {
							if (task.getTitle().equals(((MainView) source).getSelectedTask())) {
								Log.debug("Removing task from main frame");
								model.removeTask(task);
							}
						}
						Log.info("Successful 'DELETE' action");
					} else {
						view.errorMessage("Check task to remove");
						Log.error("Wasn`t choose task for remove");
					}
				} catch (IOException e) {
					view.errorMessage(e);
					Log.error("IOException");
				}
		}
	}

