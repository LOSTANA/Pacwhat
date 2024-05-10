package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.Components.Enemy1;
import main.Components.Enemy2;
import main.Components.Item;
import main.Components.Player;
import main.State.PlayerWay;

public class Maingame extends JFrame {

	Maingame stage = this;

	private JLabel backgroundMap;
	private Player player;
	private Enemy1 enemy;
	private Enemy2 enemy2;
	private Item item[][] = new Item[750][850];

	public Maingame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	public Player getPlayer() {
		return player;
	}

	public Enemy1 getEnemy() {
		return enemy;
	}
	
	public Enemy2 getEnemy2() {
		return enemy2;
	}
	
	public Item[][] getItem(){
		
		return item;
	}
	private void initData() {

		backgroundMap = new JLabel(new ImageIcon("img/background/Background.jpg"));

		setSize(750, 850);
		setContentPane(backgroundMap);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player = new Player(this);
		enemy = new Enemy1(this);
		enemy2 = new Enemy2(this);
		
		for (int i = 30; i < 700; i += 30) {
			for (int j = 30; j < 800; j += 35) {
				item[i][j] = new Item(this);
				item[i][j].setX(i);
				item[i][j].setY(j);
			}
		}


	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		add(player);
		add(enemy);
		add(enemy2);

		for (int i = 30; i < 700; i += 30) {
			for (int j =30; j < 800; j += 35) {
				if (item[i][j] != null) {
					add(item[i][j]);
				} else {
					continue;
				}
			}
		}

		setVisible(true);
	}

	private void addEventListener() {

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
						break;
					} else {
						break;
					}

				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
						break;
					} else {
						break;
					}

				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isTopWallCrash()) {
						player.up();
						break;
					} else {
						break;
					}
				case KeyEvent.VK_DOWN:
					if (!player.isDown() && !player.isBottomWallCrash()) {
						player.down();
						break;
					} else {
						break;
					}
				case KeyEvent.VK_SPACE:
					System.out.println(player.getX() + " , " + player.getY());

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

			@Override
			public void keyTyped(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (player.isLeft() && player.isLeftWallCrash()) {
						player.setLeft(false);
					}

				case KeyEvent.VK_RIGHT:
					if (player.isRight() && player.isRightWallCrash()) {
						player.setRight(false);
					}
					break;
				case KeyEvent.VK_UP:
					if (player.isUp() && player.isTopWallCrash()) {
						player.setUp(false);
					}

				case KeyEvent.VK_DOWN:
					if (player.isDown() && player.isBottomWallCrash()) {
						player.setDown(false);
					}

				}

			}

		});

	}

	public static void main(String[] args) {
		new Startgame();

	}

}
