package id.its.spaceinv;

public class EnemyBullet extends gameObject{
	
	private final int speed = 3;
	
	public EnemyBullet(int x,int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		initEnemyBullet();
	}
	
	private void initEnemyBullet(){
		loadImage("src/resources/enemybullet1.png");
		getImageDimensions();
	}
	
	public void move() {
		// TODO Auto-generated method stub
		y += speed;
		
	}
}
