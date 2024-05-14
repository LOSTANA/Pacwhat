package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Startgame extends JFrame {

	
	private JButton startButton;
	private JPanel panel1;
	private JPanel panel2;
	
	private DrawLogo drawLogo;

	public Startgame() {
		initData();
		setInitLayout();

	}

	private void initData() {

		setSize(750, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		drawLogo = new DrawLogo();
		
		setLayout(new BorderLayout());
		drawLogo.setBackground(Color.BLACK);
		panel2 = new JPanel();
		panel2.setBackground(new Color(153,204,255));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20,40));
		
		startButton = new JButton("Start Game");
		startButton.setBackground(Color.ORANGE);
		startButton.setPreferredSize(new Dimension(130,50));

	}


	private void setInitLayout() {
		
		add(drawLogo, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);

		panel2.add(startButton);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Maingame start = new Maingame();
				dispose();

			}
		});
		
	}
	
	static class DrawLogo extends JPanel{
		private Image logo;
		private Image pacman;
		private Image ghost1;
		private Image ghost2;
		private Image ghost3;
		
		
		public DrawLogo() {
			logo = new ImageIcon("img/pacman_logo.png").getImage();
			pacman = new ImageIcon("img/pacman/pac4_R.png").getImage();
			ghost1 = new ImageIcon("img/ghost1/cyanR2.png").getImage();
			ghost2 = new ImageIcon("img/ghost2/pinkR1.png").getImage();
			ghost3 = new ImageIcon("img/ghost3/redR3.png").getImage();
			
			JLabel label = new JLabel();
			add(label);
		}
		
		
		@Override
		public void paint(Graphics g) {
			
			super.paint(g);;
			g.drawImage(logo, 130, 161, 500,161,null);
			g.drawImage(pacman, 210, 600, 50, 50 , null);
			g.drawImage(ghost1, 290, 600, 50, 50 , null);
			g.drawImage(ghost2, 370, 600, 50, 50 , null);
			g.drawImage(ghost3, 450, 600, 50, 50 , null);
			
		}

	}
	
	


}
