package controller.actions;

import model.ManagerModel;
import model.TaskInterface;
import org.slf4j.*;
import view.List;
import view.MainView;
import view.ShowView;
import java.awt.event.ActionListener;

public class ActionCloseFilter implements Action {
    private static final Logger Log = LoggerFactory.getLogger(ActionCloseFilter.class);
    @Override
    public void execute(ActionListener listen, Object source, TaskInterface model, List view) {
            if (source instanceof ShowView) {
                ((ShowView) source).close();
                Log.info("Filter frame successful closed");
            }
    }
    }

