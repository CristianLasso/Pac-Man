package model;

public class PacMan {
	
	public static final int ADVANCE = 5;
	
	private int radius;
	private int LayoutX;
	private int LayoutY;
	private int wait;
	private Move state;
	private int bounces;
	private boolean stoped;
	
	
	public PacMan(int r, int x, int y, int w, int b, boolean s, Move st) {
		radius = r;
		LayoutX = x;
		LayoutY = y;
		wait = w;
		bounces = b;
		stoped = s;
		state = st;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getLayoutX() {
		return LayoutX;
	}

	public void setLayoutX(int layoutX) {
		LayoutX = layoutX;
	}

	public int getLayoutY() {
		return LayoutY;
	}

	public void setLayoutY(int layoutY) {
		LayoutY = layoutY;
	}

	public int getWait() {
		return wait;
	}

	public void setWait(int wait) {
		this.wait = wait;
	}

	public int getBounces() {
		return bounces;
	}

	public void setBounces(int bounces) {
		this.bounces = bounces;
	}

	public boolean isStoped() {
		return stoped;
	}

	public void setStoped(boolean stoped) {
		this.stoped = stoped;
	}
	
	public void move(int max) {
		switch(state) {
			case RIGHT:
				if(LayoutX+ADVANCE+radius>max) {
					state = Move.LEFT;
					LayoutX = max-radius;
				}else {
					LayoutX = LayoutX+ADVANCE;					
				}
			break;
			case LEFT:
				if(LayoutX-ADVANCE-radius<0) {
					state = Move.RIGHT;
					LayoutX = radius;
				}else {
					LayoutX = LayoutX-ADVANCE;					
				}
			break;
		}
	}
	
}
