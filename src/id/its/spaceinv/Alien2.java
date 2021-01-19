package id.its.spaceinv;

public class Alien2 extends Alien{
	
	private boolean check=true;
	private int moveCount=0;
	public Alien2(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		initAlien();
	}
	
	public void move() {
			
			if(isCheck()==true) {
				x += 2;
				moveCount++;
				if(moveCount>=100) {
					setCheck(false);
				}
			}
			if(isCheck()==false) {
				x -= 2;
				moveCount--;
				if(moveCount<=00) {
					setCheck(true);
				}
			}
			y += 1;
		}
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
}
