package id.its.spaceinv;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Spaceship extends gameObject implements KeyListener{
	
	private int dx;
	private int dy;
	private int speedX;
	private int speedY;
	private List<Missile> missiles;
	private long lastShotTime;
	private boolean isFiring;
	
	public Spaceship(int x, int y,int speedX, int speedY) {
		super(x, y);
		this.speedX = speedX;
		this.speedY = speedY;
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
		
		if(isFiring && System.currentTimeMillis() - lastShotTime > 200) {
			fire();
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
			if(System.currentTimeMillis() - lastShotTime > 200) {
				isFiring = true;
			}
		}
		else if(key == KeyEvent.VK_LEFT) {
			dx = -speedX;
		}
		else if(key == KeyEvent.VK_RIGHT) {
			dx = speedX;
		}
		else if(key == KeyEvent.VK_UP) {
			dy = -speedY;
		}
		else if(key == KeyEvent.VK_DOWN) {
			dy = speedY;
		}
		
	}
	
	private void fire() {
		// TODO Auto-generated method stub
		missiles.add(new Missile(x + 5 , y - height));
		lastShotTime = System.currentTimeMillis();
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
		else if(key == KeyEvent.VK_SPACE) {
			isFiring = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
