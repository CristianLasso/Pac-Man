package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PacManTest {
	
	private PacMan pac;
	
	public void setupScenary1() {
		pac = new PacMan(50, 50, 50, 10, 50, false, Move.RIGHT);
	}

	@Test
	public void testGenerate() {
		setupScenary1();
		
		assertNotNull("The PacMan is different to null.", pac);
	}

}
