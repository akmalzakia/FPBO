package id.its.spaceinv;

import java.awt.Point;

import java.util.ArrayList;
import java.util.HashMap;


public class LevelData {
	private static String currentLevel;
	
	private static ArrayList<String> levelList;
	private static HashMap<String, AlienBoss> bossList;
	private static HashMap<String, Point> playerList;
	private static HashMap<String,ArrayList<Alien>> aliensList;
	
	public static final String LEVEL1 = "Level 1";
	
	public static void init() {
		// TODO Auto-generated method stub
		levelList = new ArrayList<String>();
		levelList.add(LEVEL1);
		
		playerList = new HashMap<String, Point>();
		bossList = new HashMap<String, AlienBoss>();
		aliensList = new HashMap<String, ArrayList<Alien>>();
		
		ArrayList<Alien> aliens;
		
		playerList.put(LEVEL1, new Point(235,550));
		bossList.put(LEVEL1, new AlienBoss(160, -3000, 100));
		aliens = new ArrayList<Alien>();
		int xtemp = 0;
		int ytemp = 0;
		
		for(int i=0;i<2;i++) {
			for(int j=0;j<12;j++) {
				aliens.add(new Alien(xtemp+20,ytemp-10));
				aliens.add(new Alien(xtemp+20,ytemp-1600));
				
				aliens.add(new Alien3(xtemp+20,ytemp-2500));
				if((j>0&&j<4) || (j>7&&j<11)) {
					aliens.add(new Alien3(xtemp+20,ytemp-5000));
				}
				xtemp+=40;
			}
			xtemp=0;
			ytemp+=40;
		}
		
		
		for(int i=0;i<2;i++) {
			for(int j=0;j<8;j++) {
				aliens.add(new Alien2(xtemp+20,ytemp-600));
				aliens.add(new Alien2(xtemp+20,ytemp-1600));
				xtemp+=40;
			}
			xtemp=0;
			ytemp+=40;
		}
		
		
//		aliens.add(new Alien(xtemp+20,ytemp-10));
		aliensList.put(LEVEL1, aliens);
		
		
//		if(playerList == null) {
//			System.out.println("already null");
//		}
//		else {
//			System.out.println("wtf");
//		}
		
	}
	
	public static void setLevel(String s) {
		// TODO Auto-generated method stub
		currentLevel = s;
//		System.out.println(s + " " + currentLevel);
//		if(s.equals(currentLevel)) {
//			System.out.println("1 ok");
//		}
		
	}
	
	public static ArrayList<Alien> getAlien(){
		if(aliensList.get(currentLevel) == null) {
			return new ArrayList<Alien>();
		}
		return aliensList.get(currentLevel);
	}
	
	public static AlienBoss getAlienBoss() {
		return bossList.get(currentLevel);
	}
	
	public static Point getPlayerPos() {
		// TODO Auto-generated method stub
//		if(playerList == null) {
////			System.out.println("null in" +  currentLevel);
//		}
//		
		Point p = playerList.get(currentLevel);
		return p;
	}
}
