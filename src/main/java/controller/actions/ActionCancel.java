package controller.actions;

import model.ManagerModel;
import model.RemoveTaskExeption;
import model.Task;
import model.TaskInterface;
import org.slf4j.*;
import view.List;
import view.MainView;
import view.MessageView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionCancel implements Action {
	private static final Logger Log = LoggerFactory.getLogger(ActionCancel.class);
    @Override
    public void execute(ActionListener listen, Object source, TaskInterface model, List view) throws RemoveTaskExeption {
			if (source instanceof MessageView) {
				((MessageView) source).close();
				Log.info("Message view was closed");
			}

				for (Task task : model.getTaskList()) {
					if (task.getTitle().equals(((MessageView) source).getTaskToRemove())) {
						Log.debug("Removing task from frame", task.getTitle());
						model.removeTask(task);
					}
				}}
	}

