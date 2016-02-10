package controller.actions;

import model.ManagerInterface;
import view.MethodsViewInterface;
import view.ShowView;
import java.awt.event.ActionListener;

public class ActionFilter implements Action {
    @Override
    public void execute(ActionListener listen, Object source, ManagerInterface model, MethodsViewInterface view) {
        MethodsViewInterface showView = new ShowView("Filter frame");
        showView.addListener(listen);
    }
}

