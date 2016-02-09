package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import model.AddTaskException;
import model.ManagerModel;
import model.RemoveTaskExeption;
import model.Task;
import org.slf4j.*;
import view.*;
import view.ActionList;
import static controller.ActionControll.getActionControll;

public class TaskController  implements ActionList, ActionListener, Observer {

	private MainView view;
	private ManagerModel model;
	private static ActionControll initialization = getActionControll();
	private static final TaskController controller= new TaskController();
	private static final Logger Log = LoggerFactory.getLogger(TaskController.class);

	private TaskController()  {
		this.view = new MainView();
		view.addListener(this);
		this.model = new ManagerModel();
		model.addObserver(this);
		initialization.init();
		}

	public static void main(String[] args) {
		getController().showTasks();
	}
	public void showTasks () {
		Log.info("Task shows on the screen");
		view.setTasks(model.getTaskList());
	}
	public static TaskController getController()
	{
		return controller;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		try {
			initialization.getAction(event.getActionCommand()).execute(this, source, model, view);
		} catch (RemoveTaskExeption removeTaskExeption) {
			removeTaskExeption.printStackTrace();
		} catch (AddTaskException e) {
			e.printStackTrace();
		}
		Log.debug("Choosing action", event.getActionCommand());
	}
	public Task getTaskToEdit() {
		Task task = model.getTask(view.getSelectedTask());
		Log.debug("Return task to set it to edit window", task);
		return task;
	}
	/**
	 * This method is called whenever the observed object is changed.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1==UPDATE) {
			Log.debug("Updating frame", arg1);
			showTasks();
		}
		if( arg1==REMOVE){
			Log.debug("Task removing from frame", arg1);
			showTasks();
		}
		if (arg1 instanceof Task) {
			Log.debug("Adding new task into the frame");
			List messageView =  new MessageView();
			messageView.setTaskToEdit((Task) arg1);
			messageView.addListener(this);
		}
	}
}
