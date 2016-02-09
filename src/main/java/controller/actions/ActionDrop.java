package controller.actions;

import model.ManagerModel;
import model.RemoveTaskExeption;
import model.Task;
import org.slf4j.*;
import view.MainView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionDrop implements Action {
	private static final Logger Log = LoggerFactory.getLogger(ActionDrop.class);
	@Override
		public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
			if (source instanceof MainView)
				try {
					if (((MainView) source).getSelectedTask() != null) {
						Log.debug("Getting task that was selected", ((MainView) source).getSelectedTask());
						for (Task task : model.getTaskList()) {
							if (task.getTitle().equals(((MainView) source).getSelectedTask())) {
								Log.debug("Removing task from main frame", task.getTitle());
								model.removeTask(task);
							}
						}
					} else {
						view.errorMessage("Check task to remove");
						Log.error("Wasn`t choose task for remove");
					}
				} catch (RemoveTaskExeption removeTaskExeption) {
					removeTaskExeption.printStackTrace();
				}
	}
	}

