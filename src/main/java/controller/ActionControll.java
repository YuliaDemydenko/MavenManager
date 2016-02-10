package controller;

import java.util.HashMap;
import java.util.Map;
import controller.actions.*;
import view.ActionList;

public class ActionControll implements ActionList {
    private Map<String, Action> map = new HashMap<>();
    public static  final ActionControll ACTION = new ActionControll();
    public void init ()
    {
        map.put(ACTION_ADD, new ActionAdd());
        map.put(ACTION_DROP, new ActionDrop());
        map.put(ACTION_EDIT, new ActionEdit());
        map.put(ACTION_SAVE, new ActionSave());
        map.put(ACTION_CANCEL, new ActionCancel());
        map.put(ACTION_CLOSE, new ActionClose());
        map.put(ACTION_SKIP, new ActionSkip());
        map.put(ACTION_APPLY, new ActionApply());
        map.put(ACTION_FILTER, new ActionFilter());
        map.put(ACTION_CLEAN, new ActionClean());
        map.put(ACTION_CLOSEFILTER, new ActionCloseFilter());


    }
    public static ActionControll getActionControll () {
        return ACTION;
    }
    public Action getAction (String key) {
        return map.get(key);
    }

}
