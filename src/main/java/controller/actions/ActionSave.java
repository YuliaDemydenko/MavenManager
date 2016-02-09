package controller.actions;

import model.AddTaskException;
import model.ManagerInterface;
import model.ManagerModel;
import model.TaskInterface;
import org.slf4j.*;
import view.AddView;
import view.List;
import view.MainView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionSave implements Action {
	private static final Logger Log = LoggerFactory.getLogger(ActionSave.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, List view) throws AddTaskException {
		if (source instanceof List) {
                model.addTask(((AddView) source).getTask());
        }
		Log.debug("Getting new task");
		((List) source).close();
		    Log.info("Add frame closed");
	}
}

