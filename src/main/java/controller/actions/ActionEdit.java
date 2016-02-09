package controller.actions;

import model.ManagerInterface;
import model.ManagerModel;
import model.TaskInterface;
import org.slf4j.*;
import view.AddView;
import view.List;
import view.MainView;
import java.awt.event.ActionListener;
import static controller.TaskController.getController;

public class ActionEdit implements Action {
    private static final Logger Log = LoggerFactory.getLogger(ActionEdit.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, List view) {
        if (((List) source).getSelectedTask() != null) {
            Log.debug(" 'UPDATE' existing task",((List) source).getSelectedTask());
            List addView = new AddView("Edit task");
            addView.addListener(listen);
            addView.setTaskToEdit(getController().getTaskToEdit());
        }
        else {
            view.errorMessage("Check task to edit");
			Log.error("Wasn`t choose task to update");
        }
    }
}


