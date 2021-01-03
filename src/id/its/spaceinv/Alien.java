package id.its.spaceinv;

public class Alien extends gameObject{
	private final int initX = 400;
	
	public Alien(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		
		initAlien();
	}
	
	private void initAlien() {
		// TODO Auto-generated method stub
		loadImage("src/resources/ufo30.png");
		getImageDimensions();
	}
	
	public void move() {
		// TODO Auto-generated method stub
		if(x < 0) {
			x = initX;
		}
		
		x -= 1;
	}
}
