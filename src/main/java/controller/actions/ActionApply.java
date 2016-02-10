package controller.actions;

import model.GetTaskExeption;
import model.ManagerInterface;

import org.slf4j.*;
import view.MethodsViewInterface;
import view.ShowView;
import java.awt.event.ActionListener;

    public class ActionApply implements Action {
        private static final Logger LOG = LoggerFactory.getLogger(ActionApply.class);
        @Override
        public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) throws GetTaskExeption {

            for (int i = 0; i < model.getTaskList().size(); i++) {
                if (model.getTaskList().getTask(i).getStartTime().after(ShowView.getStartTimeField()) &&
                        model.getTaskList().getTask(i).getEndTime().before(ShowView.getEndTimeField())) {
                    LOG.debug("Selecting task from filter interval", model.getTaskList().getTask(i).getTitle());
                    view.buttonMap.get(model.getTaskList().getTask(i).getTitle()).setVisible(true);
                    LOG.info("Show sorted task");
                } else {

                    view.buttonMap.get(model.getTaskList().getTask(i).getTitle()).setVisible(false);
                }
                ((ShowView) source).close();
                LOG.info("Filter frame closed");
            }
        }
    }

