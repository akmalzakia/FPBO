package id.its.spaceinv;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;


import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener{
	private Timer timer;
	private Spaceship spaceship;
	private List<Alien> aliens;
	private boolean ingame;
	private final int spaceshipStartX = 235;
	private final int spaceshipStartY = 550;
	private final int boxWidth = 500;
	private final int boxHeight = 600;
	private final int delay = 15;
	
	private final int [][] pos = {
			
			{100,100},{200,200}
	        
	};
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		// TODO Auto-generated method stub
		setVisible(true);
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		ingame = true;
		
		setPreferredSize(new Dimension(boxWidth,boxHeight));
		spaceship = new Spaceship(spaceshipStartX, spaceshipStartY,4,4);
		
		initAliens();
		
		timer = new Timer(delay, this);
		timer.start();
	}
	
	private void initAliens() {
		// TODO Auto-generated method stub
		aliens = new ArrayList<Alien>();
		for(int[] p : pos) {
			aliens.add(new Alien(p[0],p[1]));
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		if(ingame) {
			drawObjects(g);
		}
		else if(aliens.isEmpty()){
			drawYouWin(g);
		}
		else {
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
		
	}
	
	
	
	private void drawObjects(Graphics g) {
		// TODO Auto-generated method stub
		if(spaceship.isVisible()) {
			
			BufferedImage image = toBufferedImage(spaceship.getImage());
			image = rotateImage(image);
			g.drawImage(image, spaceship.getX(), spaceship.getY(), this);
			
		}
		
		List<Missile> missile = spaceship.getMissiles();
		
		for(Missile m : missile) {
			if(m.isVisible()) {
				BufferedImage image = toBufferedImage(m.getImage());
				image = rotateImage(image);
				g.drawImage(image, m.getX(), m.getY(), this);
			}
		}
		
		for(Alien a : aliens) {
			if(a.isVisible()) {
				g.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Aliens left : " + aliens.size(), 5, 15);
	}
	
	private void drawYouWin(Graphics g){
		// TODO Auto-generated method stub
		String msg = "You Win";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (boxWidth - fm.stringWidth(msg)) / 2, (boxHeight - fm.getHeight()) / 2);
	}

	private void drawGameOver(Graphics g) {
		// TODO Auto-generated method stub
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (boxWidth - fm.stringWidth(msg)) / 2, (boxHeight - fm.getHeight()) / 2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		inGame();
		
		updateShip();
		updateMissiles();
		updateAliens();
		
		checkCollisions();
		
		repaint();
	}
	
	private void inGame() {
		// TODO Auto-generated method stub
		if(!ingame) {
			timer.stop();
		}
	}
	
	private void updateShip() {
		// TODO Auto-generated method stub
		if(spaceship.isVisible()) {
			spaceship.move();
		}
	}
	
	private void updateMissiles() {
		// TODO Auto-generated method stub
		List<Missile> ms = spaceship.getMissiles();
		for(int i = 0; i < ms.size(); i++) {
			Missile m = ms.get(i);
			
			if(m.isVisible()) {
				m.move();
			}
			else {
				ms.remove(i);
			}
		}
	}
	
	private void updateAliens() {
		// TODO Auto-generated method stub
		if(aliens.isEmpty()) {
			ingame = false;
			return;
		}
		
		for(int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			
			if(a.isVisible()) {
				a.move();
			}
			else {
				aliens.remove(i);
			}
		}
	}
	
	private void checkCollisions() {
		// TODO Auto-generated method stub
		Rectangle ship = spaceship.getBounds();
		
		for(Alien a : aliens) {
			Rectangle alien = a.getBounds();
			if(ship.intersects(alien)) {
				spaceship.setVisible(false);
				a.setVisible(false);
				ingame = false;
			}
		}
		
		List<Missile> ms = spaceship.getMissiles();
		
		for(Missile m : ms) {
			Rectangle missile = m.getBounds();
			
			for(Alien a : aliens) {
				Rectangle alien = a.getBounds();
				
				if(missile.intersects(alien)) {
					m.setVisible(false);
					a.setVisible(false);
				}
			}
		}
	}
	
	private class TAdapter extends KeyAdapter {
		// TODO Auto-generated method stub
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			spaceship.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			spaceship.keyPressed(e);
		}
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	public BufferedImage rotateImage(BufferedImage image) {
		final double rads = Math.toRadians(-90);
		final double sin = Math.abs(Math.sin(rads));
		final double cos = Math.abs(Math.cos(rads));
		final int w  = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
		final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
		final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		final AffineTransform at = new AffineTransform();
		at.translate(w/2, h/2);
		at.rotate(rads,0,0);
		at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
		final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		rotateOp.filter(image, rotatedImage);
		
		return rotatedImage;
	}
	
	
	
}
