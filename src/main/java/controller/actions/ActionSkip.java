package controller.actions;

import model.ManagerModel;
import org.apache.log4j.Logger;
import view.MainView;
import view.MessageView;
import java.awt.event.ActionListener;


public class ActionSkip implements Action {
    private static final Logger Log = Logger.getLogger(ActionSkip.class);
    @Override
    public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {
	        Log.debug("An action 'SKIP' occurs");
			if (source instanceof MessageView)
				((MessageView) source).close();
			Log.info("Message window closed");

    }
}
