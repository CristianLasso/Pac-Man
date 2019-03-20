package thread;

import ui.PacManController;

public class PacManThread extends Thread{
	
	private PacManController pc;

	public PacManThread(PacManController pc){
		this.pc = pc;
	}
	
	@Override
	public void run() {
		pc.update();
	}
	
}

