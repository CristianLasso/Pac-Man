package thread;

import model.PacMan;
import ui.PacManController;

public class MoveThread extends Thread{
	
	private PacManController pc;
	private PacMan pacman;

	public MoveThread(PacManController pc, PacMan pacman){
		this.pc = pc;
		this.pacman = pacman;
	}
	
	@Override
	public void run() {
		
		while(true) {
				pacman.move((int)pc.getWith());
				try {
					sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
	}
	
}

