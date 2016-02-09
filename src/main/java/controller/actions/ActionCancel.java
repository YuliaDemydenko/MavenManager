package controller.actions;

import model.*;
import org.slf4j.*;
import view.List;
import view.MainView;
import view.MessageView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionCancel implements Action {
	private static final Logger Log = LoggerFactory.getLogger(ActionCancel.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, List view) throws RemoveTaskExeption {
			if (source instanceof MessageView) {
				((MessageView) source).close();
				Log.info("Message view was closed");
			}

				for (TaskInterface task : model.getTaskList()) {
					if (task.getTitle().equals(((MessageView) source).getTaskToRemove())) {
						Log.debug("Removing task from frame", task.getTitle());
						model.removeTask(task);
					}
				}}
	}

