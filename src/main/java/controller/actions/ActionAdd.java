package controller.actions;

import model.ManagerInterface;
import model.ManagerModel;
import model.TaskInterface;
import org.slf4j.*;
import view.AddView;
import view.List;
import view.MainView;
import java.awt.event.ActionListener;

public class ActionAdd implements Action {
    private static final Logger Log = LoggerFactory.getLogger(ActionAdd.class);
    public void execute (ActionListener listen, Object source, ManagerInterface model, List view)
         {
            List addView = new AddView("Add task");
            addView.addListener(listen);
            Log.info("Successful 'ADD' action");
        }
}

