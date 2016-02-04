package controller.actions;

import model.ManagerModel;
import view.*;
import java.awt.event.ActionListener;

public interface Action {
  void execute (ActionListener listen, Object source, ManagerModel model, MainView view);
}