package model;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import org.slf4j.*;

public class ManagerModel  extends  Observable implements ManagerInterface, view.ActionList{

	private ArrayTaskList taskList = new ArrayTaskList();
	private File file = new File(FILE_PATH, FILE_NAME);
		private static final Logger Log = LoggerFactory.getLogger(ManagerModel.class);

	public ManagerModel(ArrayTaskList taskList, File file) {
		this.taskList = taskList;
		this.file = file;
	}

	public ManagerModel() throws AddTaskException {
		readTaskList(taskList);
		new TimerThread(this);
	}
	@Override
	public void notify(Object obj) {
		Log.debug("Setting changes to Observer");
		setChanged();
		notifyObservers(obj);
	}
	@Override
	public void addTask(TaskInterface task) throws AddTaskException{
		for (Task checkTask : taskList) {
			Log.debug("Adding new task into task list");
			if (checkTask.getTitle().equals(task.getTitle())) {
				Log.debug("Comparing task's titles " + checkTask.getTitle() + task.getTitle());
				try {
					Log.debug("Deleting existing task" + checkTask.getTitle());
					removeTask(checkTask);
				} catch (RemoveTaskExeption removeTaskExeption) {
					Log.debug("Error! RemoveTaskExeption: " + removeTaskExeption);
					System.err.print("Error: " + removeTaskExeption);
				}
			}
		}
		try {
			Log.debug("Adding task: " + task.getTitle());
			taskList.addTask(task);
		} catch (AddTaskException e) {
			Log.debug("Cann`t add task: " + task.getTitle());
			System.err.print("Error: " + e);
		}
		try {
			Log.debug("Writing task to task list and file" + task.getTitle() + file);
			textWriter(taskList, file);
		} catch (IOException e) {
			Log.error("Can`t write to file. IOExeption" + e);
			e.printStackTrace();
		}
		notify(UPDATE);
		Log.info("Task list updated");
	}
	@Override
	public ArrayTaskList getTaskList() {
		Log.debug("Returning the existing task list: " + this.taskList);
		return this.taskList;
	}
	public void textReader(TaskList tasks, File fileName) throws IOException, ParseException, AddTaskException {
		Log.debug("Adding tasks into the frame");
			TaskIO.textReader(tasks, fileName);

	}
	public void textWriter(TaskList tasks, File fileName) throws IOException {
		Log.debug("Adding tasks into the file");
		TaskIO.textWriter(tasks, fileName);
	}
	@Override
	public void removeTask(TaskInterface task) throws RemoveTaskExeption {
		Log.debug("Removing tasks from the list");
		for (Task tas : taskList) {
			if (tas.getTitle().equals(task.getTitle())) {
				taskList.removeTask(task);
				Log.debug("Deleting task: " + task.getTitle());
			}
		}
		try {
			textWriter(taskList, file);
		} catch (IOException e) {
			Log.error ("Can`t write task to file. IOExeption" + e);
			e.printStackTrace();
		}
		notify(REMOVE);
	}
	@Override
	public Task getTask(String title) throws TaskNotFoundExeption {
		for (Task task : taskList) {
			Log.debug("Selecting task from the list: " + task.getTitle());
			if(task.getTitle().equals(title))
				return task;
		}

		throw new TaskNotFoundExeption("Error: Task was not found!");
	}
	@Override
	public void readTaskList(ArrayTaskList tasks) throws AddTaskException {
		try {
			textReader(tasks,file);
		} catch (IOException e) {
			Log.error("Can`t read task from the frame. IOException e", e);
			taskList = new ArrayTaskList() ;
		} catch (ParseException e) {
			Log.error("ParseException e", e);
			e.printStackTrace();
		}
	}

}
