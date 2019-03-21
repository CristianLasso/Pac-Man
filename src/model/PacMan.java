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
	
	private int angle;
	private int length;
	private boolean keep;
	
	
	public PacMan(int r, int x, int y, int w, int b, boolean s, Move st) {
		radius = r;
		LayoutX = x;
		LayoutY = y;
		wait = w;
		bounces = b;
		stoped = s;
		state = st;
		
		keep = true;
		length = 270;
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
	
	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void move(int max, int maxY) {
		switch(state) {
			case RIGHT:
				angle = 0;
				mouth();
				if(LayoutX+ADVANCE+radius<max) {

					LayoutX = LayoutX+ADVANCE;
				}else {
					state = Move.LEFT;
					LayoutX = max-radius;					
				}
			break;
			case LEFT:
				angle = 180;
				mouth();
				if(LayoutX-ADVANCE-radius>0) {
					LayoutX = LayoutX-ADVANCE;
				}else {
					state = Move.RIGHT;
					LayoutX = radius;
				}
			break;
			case UP:
				angle = 270;
				mouth();
				if(LayoutY-ADVANCE-radius>0) {
					LayoutY = LayoutY-ADVANCE;
				}else {
					state = Move.DOWN;
					LayoutY = radius;
				}
			break;
			case DOWN:
				angle = 90;
				mouth();
				if(LayoutY+ADVANCE+radius<maxY) {

					LayoutY = LayoutY+ADVANCE;
				}else {
					state = Move.UP;
					LayoutY = maxY-radius;					
				}
			break;
		}
	}
	
	public void mouth() {
		if(keep) {
			length = length+4;
			//angle = angle+2;
		}
		if(length>=360) {
			keep = false;
		}
		
		if(!keep) {
			length = length-4;
			//angle = angle-2;
		}
		if(length<270) {
			keep = true;
		}
	}
	
}
