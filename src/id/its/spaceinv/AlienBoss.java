package id.its.spaceinv;

import java.util.ArrayList;
import java.util.List;

public class AlienBoss extends gameObject{
	
	private int hp;
	private boolean readyToShoot = false;
	private final int positionY = 20;
	private List<EnemyBullet> bullet;
	private final int speedY = 2;
	private final int speedX = 2;
	private final int maxX = 360;
	private final int minX = 0;
	private int moveRightDirection = 1;
	private long lastShotTime = 0;
	private int shot = 0;
	private long lastTripleShotTime = 0;
	

	public AlienBoss(int x, int y ,int hp) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.hp = hp;
		init();
	}
	
	private void init() {
		bullet = new ArrayList<EnemyBullet>();
		loadImage("src/resources/enemyboss1.png");
		getImageDimensions();
	}
	
	public int getPositionY() {
		return positionY;
	}
	
	public boolean isReadyToShoot() {
		return readyToShoot;
	}
	
	public void setReadyToShoot(boolean readyToShoot) {
		this.readyToShoot = readyToShoot;
	}
	
	public void initialMove() {
		// TODO Auto-generated method stub
		y += speedY;
	}
	
	public void bossBehavior() {
		
		if(hp > 0) {
			
			
			if(y == positionY) {
				x += (speedX * moveRightDirection);
			}
			
			if(x == maxX || x == minX) {
				moveRightDirection *= -1;
			}
			
			
			tripleShot();
			
		}
		else {
			die();
		}
		
	}
	
	private void tripleShot() {
		
		if(shot < 3) {
			if(System.currentTimeMillis() - lastShotTime > 200) {
				shoot();
				shot++;
			}
		}
		else {
			if(System.currentTimeMillis() - lastShotTime > 1500) {
				shot = 0;
			}
		}
		
		
		
	}
	
	private void shoot() {
		bullet.add(new EnemyBullet(x + 5, y + height));
		bullet.add(new EnemyBullet(x + 115, y + height));
		lastShotTime = System.currentTimeMillis();
	}
	
	private void die() {
		setVisible(false);
	}
	
	public List<EnemyBullet> getBullet(){
		return bullet;
	}
	
	public void decreaseHP(int damage) {
		hp -= damage;
	}
	
	public int getHp() {
		return hp;
	}
}
