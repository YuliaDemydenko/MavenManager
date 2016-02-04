package model;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import org.apache.log4j.*;

public class ManagerModel  extends  Observable implements view.ActionList{

	private ArrayTaskList taskList = new ArrayTaskList();
	private File file = new File(FILE_PATH, FILE_NAME);
	private static final Logger Log = Logger.getLogger(ManagerModel.class);

	public ManagerModel() {
		readTaskList(taskList);
		new TimerThread(this);
	}
	public void notify(Object obj) {
		Log.debug("Setting changes to Observer");
		setChanged();
		notifyObservers(obj);
		Log.debug("Notifying Observer about changes");
	}
	public void addTask(Task task) throws IOException {
		Log.debug("Adding new task into task list");
		for (Task checkTask : taskList) {
			Log.debug("Adding new task into task list");
			if (checkTask.getTitle().equals(task.getTitle())) {
				Log.debug("Comparing task's titles ");
				removeTask(checkTask);
			}
		}
		try {
			taskList.add(task);
		} catch (AddTaskException e) {
			e.printStackTrace();
		}
		textWriter(taskList,file);
		notify(UPDATE);
		Log.info("Task list updated");
	}
	public ArrayTaskList getTaskList() {
		Log.debug("Returning the existing task list");
		return this.taskList;
	}
	public void textReader(TaskList tasks, File fileName) throws IOException, ParseException {
		Log.debug("Reading tasks from file");
		Log.debug("Adding tasks into the frame");
		TaskIO.readText(tasks, fileName);
	}
	public void textWriter(TaskList tasks, File fileName) throws IOException {
		Log.debug("Reading tasks from the frame");
		Log.debug("Adding tasks into the file");
		TaskIO.writeText(tasks, fileName);
	}
	public void removeTask(Task task) throws IOException {
		Log.debug("Removing tasks from the list");
		for (Task tas : taskList) {
			if (tas.getTitle().equals(task.getTitle())) {
				try {
					taskList.remove(task);
				} catch (RemoveTaskExeption removeTaskExeption) {
					removeTaskExeption.printStackTrace();
				}
			}
		}
		textWriter(taskList,file);
		notify(REMOVE);
	}
	public Task getTask(String title) {
		Log.debug("Selecting task from the list");
		for (Task task : taskList) {
			if(task.getTitle().equals(title)) 
				return task;
		}
		return null;
	}
	public void readTaskList(ArrayTaskList tasks) {
		try {
			Log.debug("Reading tasks from the frame");
			textReader(tasks,file);
		} catch (IOException e) {
			Log.error("IOException e", e);
			taskList = new ArrayTaskList() ;
		} catch (ParseException e) {
			Log.error("ParseException e", e);
			taskList = new ArrayTaskList() ;
		}
	}
}
