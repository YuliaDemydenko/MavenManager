package controller.actions;

import model.GetTaskExeption;
import model.ManagerInterface;
import view.MethodsViewInterface;
import org.slf4j.*;
import java.awt.event.ActionListener;

public class ActionClean implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ActionClean.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) throws GetTaskExeption {
         for (int i = 0; i < model.getTaskList().size(); i++) {
            view.buttonMap.get(model.getTaskList().getTask(i).getTitle()).setVisible(true);
             LOG.info("Show all task on the main frame");
        }
    }
}
