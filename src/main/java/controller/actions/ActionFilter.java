package controller.actions;

import model.ManagerModel;
import org.apache.log4j.Logger;
import view.MainView;
import view.ShowView;
import java.awt.event.ActionListener;

public class ActionFilter implements Action {
    private static final Logger Log = Logger.getLogger(ActionFilter.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
        Log.debug("Creating hew filter frame");
        ShowView showView = new ShowView("Edit task");
        showView.addListener(listen);
    }
}

