package controller.actions;

import model.*;
import org.slf4j.*;
import view.List;
import view.MainView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionDrop implements Action {
	private static final Logger Log = LoggerFactory.getLogger(ActionDrop.class);
	@Override
		public void execute(ActionListener listen, Object source, ManagerInterface model, List view) {
			if (source instanceof List)
				try {
					if (((List) source).getSelectedTask() != null) {
						Log.debug("Getting task that was selected", ((List) source).getSelectedTask());
						for (Task task : model.getTaskList()) {
							if (task.getTitle().equals(((List) source).getSelectedTask())) {
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

