package controller.actions;

import model.*;
import org.slf4j.*;
import view.MethodsViewInterface;

import java.awt.event.ActionListener;

public class ActionDrop implements Action {
	private static final Logger LOG = LoggerFactory.getLogger(ActionDrop.class);
	@Override
		public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) {
			if (source instanceof MethodsViewInterface)
				try {
					if (((MethodsViewInterface) source).getSelectedTask() != null) {
						LOG.debug("Getting task that was selected", ((MethodsViewInterface) source).getSelectedTask());
						for (Task task : model.getTaskList()) {
							if (task.getTitle().equals(((MethodsViewInterface) source).getSelectedTask())) {
								LOG.debug("Removing task from main frame", task.getTitle());
								model.removeTask(task);
							}
						}
					} else {
						view.errorMessage("Check task to remove");
						LOG.error("Wasn`t choose task for remove");
					}
				} catch (RemoveTaskExeption removeTaskExeption) {
					removeTaskExeption.printStackTrace();
				}
	}
	}

