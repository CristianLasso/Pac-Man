package thread;

import javafx.application.Platform;
import ui.PacManController;

public class ControlThread extends Thread{
	
	private PacManController pcc;
	
	public ControlThread(PacManController pcc) {
		this.pcc = pcc;
	}
	
	public void run() {
		while (true) {
			PacManThread pt = new PacManThread(pcc);
			Platform.runLater(pt);

			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
