package controller.actions;

import model.ManagerModel;
import model.TaskInterface;
import org.slf4j.*;
import view.List;
import view.MainView;
import view.ShowView;
import java.awt.event.ActionListener;

public class ActionFilter implements Action {
    private static final Logger Log = LoggerFactory.getLogger(ActionFilter.class);
    @Override
    public void execute(ActionListener listen, Object source, TaskInterface model, List view) {
        List showView = new ShowView("Filter frame");
        showView.addListener(listen);
    }
}

