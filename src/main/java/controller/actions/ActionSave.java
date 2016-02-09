package controller.actions;

import model.AddTaskException;
import model.ManagerModel;
import org.slf4j.*;
import view.AddView;
import view.List;
import view.MainView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionSave implements Action {
	private static final Logger Log = LoggerFactory.getLogger(ActionSave.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
		if (source instanceof AddView) {

            try {
                model.addTask(((List) source).getTask());
            } catch (AddTaskException e) {
                e.printStackTrace();
            }
        }
		Log.debug("Getting new task");
		((List) source).close();
		    Log.info("Add frame closed");
	}
}

