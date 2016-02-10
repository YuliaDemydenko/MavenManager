package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import model.*;
import org.slf4j.*;
import view.*;
import view.ActionList;
import static controller.ActionControll.getActionControll;

public class TaskController  implements ActionList, ActionListener, Observer {

	private MethodsViewInterface view;
	private ManagerInterface model;

	public static ActionControll initialization = getActionControll();
	public static final TaskController CONTROLLER = new TaskController();
	private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

	public TaskController()  {
		this.view = new MainView();
		view.addListener(this);
		try {
			this.model = new ManagerModel();
		} catch (AddTaskException e) {
			e.printStackTrace();
		}
		model.addObserver(this);
		initialization.init();
		}

	public static void main(String[] args) {
		getController().showTasks();
	}
	public void showTasks () {
		LOG.info("Task shows on the screen");
		view.setTasks(model.getTaskList());
	}
	public static TaskController getController()
	{
		return CONTROLLER;
	}

	@Override
	public  void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		try {
			initialization.getAction(event.getActionCommand()).execute(this, source, model, view);
	} catch (RemoveTaskExeption removeTaskExeption) {
		removeTaskExeption.printStackTrace();
	} catch (AddTaskException e) {
		e.printStackTrace();
	} catch (GetTaskExeption getTaskExeption) {
		getTaskExeption.printStackTrace();
	}
		LOG.debug("Choosing ACTION", event.getActionCommand());
	}
	public TaskInterface getTaskToEdit() throws TaskNotFoundExeption {
		TaskInterface task = model.getTask(view.getSelectedTask());
		LOG.debug("Return task to set it to edit window", task);
		return task;
	}
	/**
	 * This method is called whenever the observed object is changed.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1==UPDATE) {
			LOG.debug("Updating frame", arg1);
			showTasks();
		}
		if( arg1==REMOVE){
			LOG.debug("Task removing from frame", arg1);
			showTasks();
		}
		if (arg1 instanceof TaskInterface) {
			LOG.debug("Adding new task into the frame");
			MethodsViewInterface messageView =  new MessageView();
			messageView.setTaskToEdit((Task) arg1);
			messageView.addListener(this);
		}
	}
}
