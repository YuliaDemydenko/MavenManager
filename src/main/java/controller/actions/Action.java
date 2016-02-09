package controller.actions;

import model.AddTaskException;
import model.ManagerModel;
import model.RemoveTaskExeption;
import model.TaskInterface;
import view.*;
import java.awt.event.ActionListener;

public interface Action {
  void execute (ActionListener listen, Object source, TaskInterface model, List view) throws RemoveTaskExeption, AddTaskException;
}