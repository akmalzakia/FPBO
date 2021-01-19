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
import java.util.List;
import javax.swing.Timer;


import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener{
	
	private CollisionEx frame;
	private Timer timer;
	private Spaceship spaceship;
	private AlienBoss alienBoss;
	private List<Alien> aliens;
	private String level = "Level 1";
	public boolean ingame;
	private int spaceshipStartX = 235;
	private int spaceshipStartY = 550;
	private final int boxWidth = 500;
	private final int boxHeight = 600;
	private final int delay = 15;
	private int bossCount = 1;
	private int totalaliens;
	private int score=0;
	
	
	public Board(CollisionEx frame) {
		this.frame = frame;
		initBoard();
	}
	
	private void initBoard() {
		LevelData.setLevel(level);
		// TODO Auto-generated method stub
		addKeyListener(new SpaceshipAdapter(this));
		setFocusable(true);
		setBackground(Color.BLACK);
		ingame = true;
		
		setPreferredSize(new Dimension(boxWidth,boxHeight));
		spaceshipStartX = LevelData.getPlayerPos().x;
		spaceshipStartY = LevelData.getPlayerPos().y;
		
		initPlayer();
		initAliens();
		initBoss();
		
		
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	
	private void initBoss() {
		alienBoss = LevelData.getAlienBoss();
	}
	
	private void initAliens() {
		// TODO Auto-generated method stub
		aliens = LevelData.getAlien();
	}
	
	private void initPlayer() {
		// TODO Auto-generated method stub
		spaceship = new Spaceship(spaceshipStartX, spaceshipStartY,4,4);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		if(ingame) {
			drawObjects(g);
		}
		else {
			if(getTotalaliens()<=0) {
				drawYouWin(g);
			}
			else {
				drawGameOver(g);
			}
		}
		
		Toolkit.getDefaultToolkit().sync();
		
	}
	
	public boolean isIngame() {
		return ingame;
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
		
		if(alienBoss.isVisible()) {
			g.drawImage(alienBoss.getImage(),alienBoss.getX(),alienBoss.getY(),this);
		}
		
		List<EnemyBullet> bullet = alienBoss.getBullet();
		
		for(EnemyBullet b : bullet) {
			if(b.isVisible()) {
				g.drawImage(b.getImage(),b.getX(),b.getY(),this);
			}
		}
		
		g.setColor(Color.WHITE);
		setTotalaliens();
		drawUI(g);
		g.drawString("Aliens left : " + (totalaliens), 5, 15);

	}
	
	private void drawUI(Graphics g) {
		// TODO Auto-generated method stub
		
		
		BufferedImage image = toBufferedImage(spaceship.getImage());
		image = rotateImage(image);
		for(int i = 0; i < spaceship.getHp(); i++) {
			g.drawImage(image,460 - (i * 30),10,null);
		}
		
	}
	
	private void drawGameOver(Graphics g) {
		// TODO Auto-generated method stub
		String msg = "Game Over";
		String finalscore = "Score :" + this.score;
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (boxWidth - fm.stringWidth(msg)) / 2, (boxHeight - fm.getHeight()) / 2 - 50);
		g.drawString(finalscore, (boxWidth - fm.stringWidth(finalscore)) / 2, (boxHeight - fm.getHeight()) / 2 + 50);
		
	}
	
	private void drawYouWin(Graphics g){
		// TODO Auto-generated method stub
		String msg = "You Win";
		String finalscore = "Score :" + this.score;
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (boxWidth - fm.stringWidth(msg)) / 2, (boxHeight - fm.getHeight()) / 2 - 50);
		g.drawString(finalscore, (boxWidth - fm.stringWidth(finalscore)) / 2, (boxHeight - fm.getHeight()) / 2 + 50);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		inGame();
		
		updateShip();
		updateMissiles();
		updateAliens();
		if(aliens.size()==0) {
			initBoss();
		}
		updateAlienBoss();
		updateEnemyBullets();
		
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
		
		if(spaceship.getHp() <= 0 || getTotalaliens()<=0) {
			spaceship.setVisible(false);
			ingame = false;
		}
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
		
		for(int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			
			if(a.isVisible()) {
				
				a.move();
				
				if(a.y > boxHeight) {
					a.setVisible(false);
				}
			}
			else {
				aliens.remove(i);
			}
		}
	}

	
	private void updateEnemyBullets() {
		List<EnemyBullet> b = alienBoss.getBullet();
		
		for(int i = 0; i < b.size(); i++) {
			EnemyBullet eb = b.get(i);
			if(eb.isVisible()) {
				eb.move();
			}
			else {
				b.remove(i);
			}
		}
	}
	
	private void updateAlienBoss() {
		if(alienBoss.isVisible()) {
			if(!alienBoss.isReadyToShoot()) {
				
				if(alienBoss.getY() < alienBoss.getPositionY()) {
					alienBoss.initialMove();
				}
				else {
					alienBoss.setReadyToShoot(true);
				}
			}
			else {
				alienBoss.bossBehavior();
			}
			
		}
		else if(bossCount>0){
			bossCount--;
			score += 1000;
		}
	}
	
	
	
	private void checkCollisions() {
		// TODO Auto-generated method stub
		Rectangle ship = spaceship.getBounds();
		Rectangle boss = alienBoss.getBounds();
		List<Missile> ms = spaceship.getMissiles();
		List<EnemyBullet> b = alienBoss.getBullet();
		
		
		for(Alien a : aliens) {
			Rectangle alien = a.getBounds();
			if(ship.intersects(alien)) {
//				spaceship.setVisible(false);
				spaceship.decreaseHP();
				a.setVisible(false);
//				ingame = false;
			}
		}
		
		for(EnemyBullet eb : b) {
			Rectangle bullet = eb.getBounds();
			
			if(ship.intersects(bullet)) {
//				spaceship.setVisible(false);
				spaceship.decreaseHP();
				eb.setVisible(false);
//				ingame = false;
			}
		}
		
		
		
		for(Missile m : ms) {
			Rectangle missile = m.getBounds();
			
			for(Alien a : aliens) {
				Rectangle alien = a.getBounds();
				
				if(missile.intersects(alien) && onBoard(m.x, m.y)) {
					score += 100;
					m.setVisible(false);
					a.setVisible(false);
				}
			}
		}
		
		for(Missile m : ms) {
			Rectangle missile = m.getBounds();
			if(boss.intersects(missile)) {
				m.setVisible(false);
				alienBoss.decreaseHP(m.getDamage());
				System.out.println(alienBoss.getHp());
			}
		}
		
		if(ship.intersects(boss)) {
			ingame=false;
		}
		
	}
	
	private boolean onBoard(int x, int y) {
		return x >= 0 && x <= boxWidth && y >=0 && y <= boxHeight;
	}
	
	private class SpaceshipAdapter extends KeyAdapter {
		// TODO Auto-generated method stub
		Board board;
		
		public SpaceshipAdapter(Board board) {
			this.board = board;
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(board.ingame) {
				spaceship.keyReleased(e);
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(board.ingame) {
				spaceship.keyPressed(e);
			}
			else {
				int key = e.getKeyCode();
				
				if(key == KeyEvent.VK_SPACE) {
					TitleScreen ts = new TitleScreen();
					frame.dispose();
					ts.setVisible(true);
					
				}
			}
			
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

	public int getTotalaliens() {
		return totalaliens;
	}

	public void setTotalaliens() {
		this.totalaliens = aliens.size()+bossCount;
	}
	
	
	
}
