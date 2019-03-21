package thread;

import javafx.application.Platform;
import ui.PacManController;

public class ControlThread extends Thread{
	
	private PacManController pc;
	
	public ControlThread(PacManController pc) {
		this.pc = pc;
	}
	
	public void run() {
		while (true) {
			PacManThread pt = new PacManThread(pc);
			Platform.runLater(pt);

			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
