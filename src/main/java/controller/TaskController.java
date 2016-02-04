package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import model.ManagerModel;
import model.Task;
import org.apache.log4j.*;
import view.*;
import view.ActionList;
import static controller.ActionControll.getActionControll;

public class TaskController  implements ActionList, ActionListener, Observer {

	private  MainView view;
	private ManagerModel model;
	private static ActionControll initialization = getActionControll();
	private static final TaskController controller= new TaskController();
	private static final Logger Log = Logger.getLogger(TaskController.class);

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
		Log.debug("An action has been occured");
		Object source = event.getSource();
		initialization.getAction(event.getActionCommand()).execute(this, source, model, view);
	}
	public Task getTaskToEdit() {
		Log.debug("Return task to set it to edit window");
		Task task = model.getTask(view.getSelectedTask());
		return task;
	}
	/**
	 * This method is called whenever the observed object is changed.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1==UPDATE) {
			Log.debug("Updating frame");
			showTasks();
		}
		if( arg1==REMOVE){
			Log.debug("Task removing from frame");
			showTasks();
		}
		if (arg1 instanceof Task) {
			Log.debug("Adding new task into the frame");
			MessageView messageView =  new MessageView();
			messageView.addMessageLabel((Task) arg1);
			messageView.addListener(this);
		}
	}
}
