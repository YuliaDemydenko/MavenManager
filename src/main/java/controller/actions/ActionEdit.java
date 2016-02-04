package controller.actions;

import model.ManagerModel;
import org.apache.log4j.Logger;
import view.AddView;
import view.MainView;
import java.awt.event.ActionListener;
import static controller.TaskController.getController;

public class ActionEdit implements Action {
    private static final Logger Log = Logger.getLogger(ActionEdit.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
		Log.debug("An action 'UPDATE' occurs");
        if (((MainView) source).getSelectedTask() != null) {
            AddView addView = new AddView("Edit task");
            addView.addListener(listen);
            addView.setTaskToEdit(getController().getTaskToEdit());
			Log.info("Successful 'UPDATE' action");
        }
        else {
            view.errorMessage("Check task to edit");
			Log.error("Wasn`t choose task to update");
        }
    }
}


