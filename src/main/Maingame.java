package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

<<<<<<< HEAD
=======
import javax.swing.ImageIcon;
>>>>>>> bbb9af715f63d33013378d4c89033502e48ece6b
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

	public Player getPlayer() {
		return player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	private void initData() {

<<<<<<< HEAD
		setSize(750, 850);
=======
		backgroundMap = new JLabel(new ImageIcon("img/background/Background.jpg"));

		setSize(750, 850);
		setContentPane(backgroundMap);
>>>>>>> bbb9af715f63d33013378d4c89033502e48ece6b
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player = new Player(this);
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
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					} else
						System.err.println("벽");
					break;

				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					} else
						System.err.println("벽");
					break;

				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isTopWallCrash()) {
						player.up();
					} else
						System.err.println("벽");
					break;
				case KeyEvent.VK_DOWN:
					if (!player.isDown() && !player.isBottomWallCrash()) {
						player.down();
					}
					System.err.println("벽");
					break;

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				case KeyEvent.VK_UP:
					player.setUp(false);
					break;
				case KeyEvent.VK_DOWN:
					player.setDown(false);
					break;
					
				}
			}
			
<<<<<<< HEAD
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
			}
=======
>>>>>>> bbb9af715f63d33013378d4c89033502e48ece6b
		});
	}

	public static void main(String[] args) {
		new Maingame();
	}

}
