package controller.actions;

import model.ManagerInterface;
import org.slf4j.*;
import view.MethodsViewInterface;

import java.awt.event.ActionListener;

public class ActionCloseFilter implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(ActionCloseFilter.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) {
            if (source instanceof MethodsViewInterface) {
                ((MethodsViewInterface) source).close();
                LOG.info("Filter frame successful closed");
            }
    }
    }

