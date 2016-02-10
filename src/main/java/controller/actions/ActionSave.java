package controller.actions;

import model.AddTaskException;
import model.ManagerInterface;
import org.slf4j.*;
import view.MethodsViewInterface;

import java.awt.event.ActionListener;

public class ActionSave implements Action {
	private static final Logger LOG = LoggerFactory.getLogger(ActionSave.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) throws AddTaskException {
		if (source instanceof MethodsViewInterface) {
                model.addTask(((MethodsViewInterface) source).getTask());
        }
		LOG.debug("Getting new task");
		((MethodsViewInterface) source).close();
		    LOG.info("Add frame closed");
	}
}

