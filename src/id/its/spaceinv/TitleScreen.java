package id.its.spaceinv;

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

public class TitleScreen extends JPanel{
	JPanel titleNamePanel, startButtonPanel;
	JLabel titleNameLabel;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 64);
	Font startFont = new Font("Times New Roman", Font.PLAIN, 30);
	JButton startButton;
	
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	
	public TitleScreen() {

		
		setPreferredSize(new Dimension(800,600));
		setFocusable(true);
		setBackground(Color.BLACK);
		
		
		titleNameLabel = new JLabel("ALIEN DESTROYER");
		titleNameLabel.setForeground(Color.WHITE);
		titleNameLabel.setFont(titleFont);
		
		
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(startFont);
		startButton.addActionListener(tsHandler);
		
		
		add(titleNameLabel);
		add(startButton);
	}
	
	public class TitleScreenHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			CollisionEx ex = new CollisionEx();
			
		}
		
	}
}
