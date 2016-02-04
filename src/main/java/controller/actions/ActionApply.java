package controller.actions;

import model.ManagerModel;
import org.apache.log4j.Logger;
import view.MainView;
import view.ShowView;
import java.awt.event.ActionListener;

    public class ActionApply implements Action {
        private static final Logger Log = Logger.getLogger(ActionApply.class);

        @Override
        public void execute(ActionListener listen, Object source, ManagerModel model, MainView view) {

            for (int i = 0; i < model.getTaskList().size(); i++) {
                Log.debug("Selecting each task");
                if (model.getTaskList().getTask(i).getStartTime().after(ShowView.getStartTimeField()) &&
                        model.getTaskList().getTask(i).getEndTime().before(ShowView.getEndTimeField())) {
                    Log.debug("Selecting task from filter interval");
                    view.buttonMap.get(model.getTaskList().getTask(i).getTitle()).setVisible(true);
                    Log.info("Show sorted task");

                } else {

                    view.buttonMap.get(model.getTaskList().getTask(i).getTitle()).setVisible(false);
                    Log.info("Hide task that not included in the filter");
                }
                ((ShowView) source).close();
                Log.info("Filter frame closed");
            }
        }
    }

