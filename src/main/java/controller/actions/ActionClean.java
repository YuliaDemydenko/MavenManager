package controller.actions;

import model.GetTaskExeption;
import model.ManagerInterface;
import model.ManagerModel;
import model.TaskInterface;
import view.List;
import view.MainView;
import org.slf4j.*;
import java.awt.event.ActionListener;

public class ActionClean implements Action {
    private static final Logger Log = LoggerFactory.getLogger(ActionClean.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, List view) throws GetTaskExeption {
         for (int i = 0; i < model.getTaskList().size(); i++) {
            view.buttonMap.get(model.getTaskList().getTask(i).getTitle()).setVisible(true);
             Log.info("Show all task on the main frame");
        }
    }
}
