package controller.actions;

import model.AddTaskException;
import model.ManagerModel;
import org.slf4j.*;
import view.AddView;
import view.MainView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionSave implements Action {
	private static final Logger Log = LoggerFactory.getLogger(ActionSave.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
			try {
				if (source instanceof AddView) {

					model.addTask(((AddView) source).getTask());
				}
				Log.debug("Getting new task");
			}
            catch (IOException e) {
				view.errorMessage(e);
				Log.error("IOException error", e);
	        	}
		((AddView) source).close();
		    Log.info("Add frame closed");
	}
}

