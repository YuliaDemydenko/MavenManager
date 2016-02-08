package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This interface contain action commands to controller.
 */
public interface ActionList {
	String ACTION_ADD = "Add";
	String ACTION_DROP = "Delete";
	String ACTION_EDIT = "Edit";
	String ACTION_SAVE = "Save";
	String ACTION_CLOSE = "Close";
	String ACTION_SKIP = "Skip";
	String ACTION_CANCEL = "Cancel";
	String FILE_NAME = "tasks.txt";
	String FILE_PATH = "src/main/resources/";
	String UPDATE = "Update";
	String REMOVE = "Remove";
	String ACTION_FILTER = "Show task";
	String ACTION_APPLY = "Apply";
	String ACTION_CLEAN = "Clean filter";
	String ACTION_CLOSEFILTER = "Close filter";


}
