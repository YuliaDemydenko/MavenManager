package controller.actions;

import model.ManagerInterface;
import org.slf4j.*;
import view.MethodsViewInterface;

import java.awt.event.ActionListener;


public class ActionSkip implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ActionSkip.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) {
			if (source instanceof MethodsViewInterface)
				((MethodsViewInterface) source).close();
			LOG.info("Message window closed");

    }
}
