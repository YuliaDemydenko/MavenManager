package controller.actions;

import model.ManagerModel;
import org.slf4j.*;
import view.List;
import view.MainView;
import view.MessageView;
import java.awt.event.ActionListener;


public class ActionSkip implements Action {
    private static final Logger Log = LoggerFactory.getLogger(ActionSkip.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
			if (source instanceof MessageView)
				((MessageView) source).close();
			Log.info("Message window closed");

    }
}
