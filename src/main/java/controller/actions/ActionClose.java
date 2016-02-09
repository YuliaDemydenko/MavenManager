package controller.actions;

import model.ManagerInterface;
import model.ManagerModel;
import model.TaskInterface;
import org.slf4j.*;
import view.AddView;
import view.List;
import view.MainView;
import java.awt.event.ActionListener;

public class ActionClose implements Action {
    private static final Logger Log = LoggerFactory.getLogger(ActionClose.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, List view) {
			if (source instanceof AddView) {
				((AddView) source).close();
				Log.info("Add frame successful closed");
	         }
    }
}
