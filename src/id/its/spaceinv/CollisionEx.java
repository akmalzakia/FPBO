package id.its.spaceinv;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class CollisionEx extends JFrame{
	public CollisionEx() {
		// TODO Auto-generated constructor stub
		initUI();
	}
	
	private void initUI() {
		// TODO Auto-generated method stub
		add(new Board());
		setResizable(false);
		pack();
		
		setTitle("Collision");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			CollisionEx ex = new CollisionEx();
			ex.setVisible(true);
		});
	}
}
