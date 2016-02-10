package controller.actions;

import model.ManagerInterface;
import model.TaskNotFoundExeption;
import org.slf4j.*;
import view.AddView;
import view.MethodsViewInterface;

import java.awt.event.ActionListener;
import static controller.TaskController.getController;

public class ActionEdit implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ActionEdit.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) {
        if (((MethodsViewInterface) source).getSelectedTask() != null) {
            LOG.debug(" 'UPDATE' existing task",((MethodsViewInterface) source).getSelectedTask());
            MethodsViewInterface addView = new AddView("Edit task");
            addView.addListener(listen);
            try {
                addView.setTaskToEdit(getController().getTaskToEdit());
            } catch (TaskNotFoundExeption taskNotFoundExeption) {
                taskNotFoundExeption.printStackTrace();
            }
        }
        else {
            view.errorMessage("Check task to edit");
            LOG.error("Wasn`t choose task to update");
        }
    }
}


