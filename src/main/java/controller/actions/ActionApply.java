package controller.actions;

import model.ManagerModel;

import org.slf4j.*;
import view.MainView;
import view.ShowView;
import java.awt.event.ActionListener;

    public class ActionApply implements Action {
        private static final Logger Log = LoggerFactory.getLogger(ActionApply.class);
        @Override
        public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {

            for (int i = 0; i < model.getTaskList().size(); i++) {
                if (model.getTaskList().getTask(i).getStartTime().after(ShowView.getStartTimeField()) &&
                        model.getTaskList().getTask(i).getEndTime().before(ShowView.getEndTimeField())) {
                    Log.debug("Selecting task from filter interval", model.getTaskList().getTask(i).getTitle());
                    view.buttonMap.get(model.getTaskList().getTask(i).getTitle()).setVisible(true);
                    Log.info("Show sorted task");
                } else {

                    view.buttonMap.get(model.getTaskList().getTask(i).getTitle()).setVisible(false);
                }
                ((ShowView) source).close();
                Log.info("Filter frame closed");
            }
        }
    }

