package controller.actions;

import model.ManagerInterface;
import org.slf4j.*;
import view.AddView;
import view.MethodsViewInterface;

import java.awt.event.ActionListener;

public class ActionClose implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ActionClose.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) {
			if (source instanceof AddView) {
				((AddView) source).close();
				LOG.info("Add frame successful closed");
	         }
    }
}
