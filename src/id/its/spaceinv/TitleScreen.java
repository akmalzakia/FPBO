package id.its.spaceinv;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleScreen extends JFrame{
	Container con;
	JPanel titleNamePanel, startButtonPanel;
	JLabel titleNameLabel;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 64);
	Font startFont = new Font("Times New Roman", Font.PLAIN, 30);
	JButton startButton;
	
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	
	public static void main(String[] args) {
		
		new TitleScreen();
	}
	
	public TitleScreen() {

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		setLayout(null);
		setVisible(true);
		con = getContentPane();
		
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 150);
		titleNamePanel.setBackground(Color.BLACK);
		titleNameLabel = new JLabel("ALIEN DESTROYER");
		titleNameLabel.setForeground(Color.WHITE);
		titleNameLabel.setFont(titleFont);
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 100);
		startButtonPanel.setBackground(Color.black);
		
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(startFont);
		startButton.addActionListener(tsHandler);
		
		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		con.add(titleNamePanel);
		con.add(startButtonPanel);
	}
	
	public class TitleScreenHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			EventQueue.invokeLater(() -> {
				CollisionEx ex = new CollisionEx();
				titleNamePanel.setVisible(false);
				ex.setVisible(true);
			});
		}
		
	}
}
