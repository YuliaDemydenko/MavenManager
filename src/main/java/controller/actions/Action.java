package controller.actions;

import model.*;
import view.*;
import java.awt.event.ActionListener;

public interface Action {
  void execute (ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view)
          throws RemoveTaskExeption, AddTaskException, GetTaskExeption;
}