package id.its.spaceinv;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class CollisionEx extends JFrame{
	private JLabel title;
	CardLayout card;
	JPanel titleMenu;
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 45);
	private Font startFont = new Font("Times New Roman", Font.PLAIN, 30);
	Container c;
	
	public CollisionEx() {
		// TODO Auto-generated constructor stub
		initUI();
	}
	
	private void initUI() {
		// TODO Auto-generated method stub
		
		card = new CardLayout();
		c = getContentPane();
		c.setLayout(card);
//		c.setBackground(Color.BLACK);
		titleMenu = new JPanel();
		JButton start = new JButton("Start");
		ButtonHandler handler = new ButtonHandler();
//		
//		getContentPane();
		titleMenu.setBackground(Color.BLACK);
//		
		setPreferredSize(new Dimension(500, 600));
//		
		title = new JLabel("ALIEN DESTROYER");
		title.setForeground(Color.WHITE);
		title.setFont(titleFont);
//		
		start.addActionListener(handler);
		start.setBackground(Color.WHITE);
		start.setFont(startFont);
		start.setForeground(Color.BLACK);
		
		
		
////		title.setHorizontalAlignment(SwingConstants.CENTER);
////		title.setVerticalAlignment(SwingConstants.CENTER);
		titleMenu.add(title);
		titleMenu.add(start);
//		add(titleMenu);
		
		
		c.add("TitleMenu", titleMenu);
		c.add("Game", new Board());
		
		
		
		setResizable(false);
		pack();
		
		setTitle("Collision");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void initGame() {
		add(new Board());
		setResizable(false);
		pack();
		
		setTitle("Collision");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			CollisionEx ex = new CollisionEx();
			ex.setVisible(true);
		});
	}
	
	public class ButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stubt
//			remove(titleMenu);
//			initGame();
			card.next(c);
		}
	}
}

