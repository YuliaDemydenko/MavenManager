package model;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import org.apache.log4j.*;

public class ManagerModel  extends  Observable implements TaskInterface, view.ActionList{

	private ArrayTaskList taskList = new ArrayTaskList();
	private File file = new File(FILE_PATH, FILE_NAME);

	public ManagerModel(File file) {
		this.file = file;
	}
	private static final Logger Log = Logger.getLogger(ManagerModel.class);

	public ManagerModel() {
		readTaskList(taskList);
		new TimerThread(this);
	}
	@Override
	public void notify(Object obj) {
		Log.debug("Setting changes to Observer");
		setChanged();
		notifyObservers(obj);
		Log.debug("Notifying Observer about changes");
	}
	public void addTask(Task task) throws AddTaskException{
		Log.debug("Adding new task into task list");
		for (Task checkTask : taskList) {
			Log.debug("Adding new task into task list");
			if (checkTask.getTitle().equals(task.getTitle())) {
				Log.debug("Comparing task's titles ");
				try {
					removeTask(checkTask);
				} catch (RemoveTaskExeption removeTaskExeption) {
					removeTaskExeption.printStackTrace();
				}
			}
		}
		try {
			taskList.addTask(task);
		} catch (AddTaskException e) {
			e.printStackTrace();
		}
		try {
			textWriter(taskList,file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		notify(UPDATE);
		Log.info("Task list updated");
	}

	@Override
	public void removeTask(Task task) throws RemoveTaskExeption {

	}
	@Override
	public ArrayTaskList getTaskList() {
		Log.debug("Returning the existing task list");
		return this.taskList;
	}
	public void textReader(TaskList tasks, File fileName) throws IOException, ParseException {
		Log.debug("Reading tasks from file");
		Log.debug("Adding tasks into the frame");
			TaskIO.textReader(tasks, fileName);

	}
	public void textWriter(TaskList tasks, File fileName) throws IOException {
		Log.debug("Reading tasks from the frame");
		Log.debug("Adding tasks into the file");
		TaskIO.textWriter(tasks, fileName);
	}
	@Override
	public Task getTask(int number) {
		return null;
	}
	@Override
	public int size() {
		return 0;
	}
	@Override
	public Iterator<Task> iterator() {
		return null;
	}
	@Override
	public void remove(Task task) throws RemoveTaskExeption {
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
		try {
			textWriter(taskList, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		notify(REMOVE);
	}
	@Override
	public Task getTask(String title) {
		Log.debug("Selecting task from the list");
		for (Task task : taskList) {
			if(task.getTitle().equals(title))
				return task;
		}
		return null;
	}
	@Override
	public void readTaskList(ArrayTaskList tasks) {
		try {
			Log.debug("Reading tasks from the frame");
			textReader(tasks,file);
		} catch (IOException e) {
			Log.error("IOException e", e);
			taskList = new ArrayTaskList() ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getTitle() {	return null;}
	@Override
	public boolean isActive() {	return false;	}
	@Override
	public void setActive(boolean active) {}
	@Override
	public void setTime(Date time) {}
	@Override
	public void setTime(Date start, Date end, long interval) {}
	@Override
	public Date getTime() {	return null;}
	@Override
	public Date getStartTime() {return null;}
	@Override
	public Date getEndTime() {return null;}
	@Override
	public long getRepeatInterval() {return 0;}
	@Override
	public boolean isRepeated() {return false;}
	@Override
	public Date nextTimeAfter(Date current) {return null;}
	@Override
	public void setTitle(String title){}
}
