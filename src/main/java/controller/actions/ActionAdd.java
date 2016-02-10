package controller.actions;

import model.ManagerInterface;
import org.slf4j.*;
import view.AddView;
import view.MethodsViewInterface;

import java.awt.event.ActionListener;

public class ActionAdd implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ActionAdd.class);
    public void execute (ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view)
         {
            MethodsViewInterface addView = new AddView("Add task");
            addView.addListener(listen);
            LOG.info("Successful 'ADD' ACTION");
        }
}

