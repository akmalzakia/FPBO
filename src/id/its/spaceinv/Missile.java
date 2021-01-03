package id.its.spaceinv;

public class Missile extends gameObject{
	
	private final int boxWidth = 390;
	private final int speed = 2;
	
	public Missile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		initMissile();
	}
	
	private void initMissile() {
		// TODO Auto-generated method stub
		loadImage("src/resources/bullet20.png");
		getImageDimensions();
	}
	
	public void move() {
		// TODO Auto-generated method stub
		x += speed;
		
		if( x > boxWidth) {
			setVisible(false);
		}
	}
}
