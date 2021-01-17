package id.its.spaceinv;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class CollisionEx extends JFrame{
	private Board board;
	public CollisionEx() {
		// TODO Auto-generated constructor stub
		LevelData.init();
		initUI();
	}
	
	private void initUI() {
		// TODO Auto-generated method stub
		board = new Board(this);
		add(board);
		setResizable(false);
		pack();
		
		
		setTitle("ALIEN DESTROYER");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			CollisionEx ex = new CollisionEx();
			ex.setVisible(true);
		});
	}
	*/

	
}


