package model;

import java.util.Date;
import java.util.Observable;
import org.slf4j.*;

public class TimerThread extends  Observable {
	private static final Logger Log = LoggerFactory.getLogger(TimerThread.class);
	private Thread worker;
	public TimerThread(ManagerModel model ) {
		Log.info("Run timer every task from task list");
		 final ManagerModel mod = model;
		worker = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					long toDay = System.currentTimeMillis();
					for (Task task: mod.getTaskList()) {
						if(toDay>=task.getStartTime().getTime() && toDay<=task.getStartTime().getTime()+1000){
							mod.notify(task);
						}
						if (toDay>=task.getEndTime().getTime() && toDay<=task.getEndTime().getTime()+1000) {
							mod.notify(task);
						}
					}
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {
						mod.notify("Couldn't stop timer.");
						Log.error("InterruptedException. Couldn't stop timer." + e);
					}
			}}
		});
		worker.start();
	}
}
