package controller.actions;

import model.*;
import org.slf4j.*;
import view.MethodsViewInterface;
import view.MessageView;
import java.awt.event.ActionListener;

public class ActionCancel implements Action {
	private static final Logger LOG = LoggerFactory.getLogger(ActionCancel.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) throws RemoveTaskExeption {
			if (source instanceof MessageView) {
				((MessageView) source).close();
				LOG.info("Message view was closed");
			}

				for (TaskInterface task : model.getTaskList()) {
					if (task.getTitle().equals(((MessageView) source).getTaskToRemove())) {
						LOG.debug("Removing task from frame", task.getTitle());
						model.removeTask(task);
					}
				}}
	}

