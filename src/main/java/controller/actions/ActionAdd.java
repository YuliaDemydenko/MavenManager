package controller.actions;

import model.ManagerModel;
import org.apache.log4j.Logger;
import view.AddView;
import view.MainView;
import java.awt.event.ActionListener;

public class ActionAdd implements Action {
    private static final Logger Log = Logger.getLogger(ActionAdd.class);
    public void execute (ActionListener listen, Object source, ManagerModel model, MainView view)
         {
           Log.debug("An action 'ADD' occurs");
            AddView addView = new AddView("Add task");
            addView.addListener(listen);
           Log.info("Successful 'ADD' action");
        }
}

