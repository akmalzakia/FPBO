package id.its.spaceinv;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Spaceship extends gameObject implements KeyListener{
	
	private int dx;
	private int dy;
	private List<Missile> missiles;
	
	public Spaceship(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	private void initialize() {
		missiles = new ArrayList<Missile>();
		loadImage("src/resources/spaceship30.png");
		getImageDimensions();
	}
	
	public void move() {
		// TODO Auto-generated method stub
		x += dx;
		y += dy;
		
		if(x < 1) {
			x = 1;
		}
		
		if(y < 1) {
			y = 1;
		}
	}
	
	public List<Missile> getMissiles(){
		return missiles;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE) {
			fire();
		}
		else if(key == KeyEvent.VK_LEFT) {
			dx = -1;
		}
		else if(key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
		else if(key == KeyEvent.VK_UP) {
			dy = -1;
		}
		else if(key == KeyEvent.VK_DOWN) {
			dy = 1;
		}
		
	}
	
	private void fire() {
		// TODO Auto-generated method stub
		missiles.add(new Missile(x + width, y + height/2 + 10));
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		else if(key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		else if(key == KeyEvent.VK_UP) {
			dy = 0;
		}
		else if(key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
