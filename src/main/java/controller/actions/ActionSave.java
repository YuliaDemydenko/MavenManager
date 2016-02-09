package controller.actions;

import model.AddTaskException;
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
    public void execute(ActionListener listen, Object source, TaskInterface model, List view) throws AddTaskException {
		if (source instanceof AddView) {
                model.addTask(((AddView) source).getTask());
        }
		Log.debug("Getting new task");
		((AddView) source).close();
		    Log.info("Add frame closed");
	}
}

