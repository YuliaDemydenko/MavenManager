package controller.actions;

import model.ManagerModel;
import org.apache.log4j.Logger;
import view.MainView;
import view.ShowView;
import java.awt.event.ActionListener;

public class ActionClean implements Action {
    private static final Logger Log = Logger.getLogger(ActionClean.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
         for (int i = 0; i < model.getTaskList().size(); i++) {
             Log.debug("Getting all tasks");
            view.buttonMap.get(model.getTaskList().getTask(i).getTitle()).setVisible(true);
             Log.info("Show all task on the main frame");
        }
    }
}
