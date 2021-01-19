package id.its.spaceinv;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Spaceship extends gameObject implements KeyListener{
	
	private int hp = 3;
	private int dx;
	private int dy;
	private int speedX;
	private int speedY;
	private List<Missile> missiles;
	private long lastShotTime;
	private boolean isFiring;
	private int shotMode;
	
	public Spaceship(int x, int y,int speedX, int speedY) {
		super(x, y);
		this.speedX = speedX;
		this.speedY = speedY;
		shotMode = 1;
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	private void initialize() {
		missiles = new ArrayList<Missile>();
		loadImage("src/resources/spaceship30.png");
		getImageDimensions();
	}
	
	public void setShotMode(int shotMode) {
		this.shotMode = shotMode;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void decreaseHP() {
		hp--;
	}
	
	public void increaseHP() {
		hp++;
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
		
		if(x > 470) {
			x = 470;
		}
		
		if(y > 570) {
			y = 570;
		}
		
		if(isFiring && System.currentTimeMillis() - lastShotTime > 400) {
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
			if(System.currentTimeMillis() - lastShotTime > 400) {
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
		if(shotMode == 0) {
			missiles.add(new Missile(x + 5 , y - height));
			
		}
		else if(shotMode == 1){
			missiles.add(new Missile(x , y - height));
			missiles.add(new Missile(x + 10,y - height));
		}
		
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
