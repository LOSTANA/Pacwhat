package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Maingame extends JFrame {

	Maingame stage = this;
	
	private JLabel backgroundMap;
	private Player player;
	private Enemy enemy;

	public Maingame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {

		backgroundMap = new JLabel(new ImageIcon("img/background/Background.jpg"));
		
		setSize(750, 850);
		setContentPane(backgroundMap);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//player = new Player(this);
		enemy = new Enemy(this);
		

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		add(player);
		add(enemy);
	}

	private void addEventListener() {

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.left();
					break;
					
				case KeyEvent.VK_RIGHT:
					player.right();
					break;
					
				case KeyEvent.VK_UP:
					player.up();
					break;
				case KeyEvent.VK_DOWN:
					player.down();
					break;
					
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
			}
		});
	}
	
	public static void main(String[] args) {
		new Maingame();
	}

}
