package id.its.spaceinv;

public class Missile extends gameObject{
	
//	private final int boxWidth = 390;
	private final int speed = 5;
	private final int damage = 2;
	
	public Missile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		initMissile();
	}
	
	public int getDamage() {
		return damage;
	}
	
	private void initMissile() {
		// TODO Auto-generated method stub
		loadImage("src/resources/bullet20.png");
		getImageDimensions();
	}
	
	public void move() {
		// TODO Auto-generated method stub
		y -= speed;
		
		if( y < 0) {
			setVisible(false);
		}
	}
}
