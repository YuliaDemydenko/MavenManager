package controller.actions;

import model.ManagerModel;
import org.apache.log4j.Logger;
import view.MainView;
import view.ShowView;
import java.awt.event.ActionListener;

public class ActionCloseFilter implements Action {
    private static final Logger Log = Logger.getLogger(ActionCloseFilter.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {

  {
            Log.debug("An action 'CLOSE' occurs");
            if (source instanceof ShowView) {
                ((ShowView) source).close();
                Log.info("Filter frame successful closed");
            }
        }
    }
    }

