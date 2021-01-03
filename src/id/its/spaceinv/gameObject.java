package id.its.spaceinv;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class gameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private boolean visibility;
	private Image image;
	
	public gameObject(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		visibility = true;
	}
	
	protected void getImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	protected void loadImage(String imageName) {
		ImageIcon icon = new ImageIcon(imageName);
		image = icon.getImage();
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visibility;
	}
	
	public void setVisible(boolean visibility) {
		// TODO Auto-generated method stub
		this.visibility = visibility;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
}

