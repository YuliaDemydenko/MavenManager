package controller.actions;

import model.AddTaskException;
import model.ManagerModel;
import org.apache.log4j.Logger;
import view.AddView;
import view.MainView;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionSave implements Action {
	private static final Logger Log = Logger.getLogger(ActionSave.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
			Log.debug("An action 'SAVE' occurs");
			try {
				if (source instanceof AddView) {
					Log.debug("Getting new task");
					model.addTask(((AddView) source).getTask());
				}
				 Log.info("Successful 'SAVE' action");
			}
            catch (IOException e) {
				view.errorMessage(e);
				Log.error("IOException error", e);
	        	}
		((AddView) source).close();
		    Log.info("Add frame closed");
	}
}

