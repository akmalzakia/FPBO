package id.its.spaceinv;

import java.util.List;
import java.awt.event.ActionListener;

public class Level1 extends Board implements ActionListener{
	private List<Alien> aliens;
	private Spaceship spaceship;
	
	private int [][] pos = {
			{200,700}, {200,600}
	};
	
	public Level1() {
		super();
		
		// TODO Auto-generated constructor stub
	}
	
}
