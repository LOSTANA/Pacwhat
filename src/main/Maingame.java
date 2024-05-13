package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.Components.Enemy1;
import main.Components.Enemy2;
import main.Components.Enemy3;
import main.Components.Item;
import main.Components.Player;

public class Maingame extends JFrame {

	Maingame stage = this;

	private JLabel backgroundMap;
	private Player player;
	private Enemy1 enemy;
	private Enemy2 enemy2;
	private Enemy3 enemy3;

	private Item[] item = new Item[239];

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

	public Item[] getItem() {
		return item;
	}

	public Enemy3 getEnemy3() {
		return enemy3;
	}

	private void initData() {

		backgroundMap = new JLabel(new ImageIcon("img/background/Background.jpg"));

		setSize(750, 850);
		setContentPane(backgroundMap);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player = new Player(this);
		enemy = new Enemy1(this);
		enemy2 = new Enemy2(this);

		enemy3 = new Enemy3(this);

		for (int i = 0; i < 239; i++) {
			if (item[i] == null)
				item[i] = new Item(this);
		}

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		int count = 1;
		add(player);
		add(enemy);
		add(enemy2);
		add(enemy3);

		for (int i = 0; i < 239; i++) {

			if (i % 17 == 0) {
				if (i == 0) {

				} else {
					if (i + 1 < 239) {
						item[i + 1].setY(item[i].getY() + 55);
					}
				}
			} else {

				if (count > 238) {
					count = count - 16;
				}

				else {
					if (i == 239) {
						add(item[i]);
					} else {
						item[i + 1].setX(item[i].getX() + 40);
						item[i + 1].setY(item[i].getY());
					}

				}
			}
			add(item[i]);
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
					} else {
						for (int i = 0; i < 324; i++) {
							if (player.getX() == item[i].getX() && player.getY() == item[i].getY()) {
								item[i].setIcon(null);
								item[i].setLocation(item[i].getX(), item[i].getY());
							}
						}
					}

				case KeyEvent.VK_RIGHT:
					if (player.isRight() && player.isRightWallCrash()) {
						player.setRight(false);
					}
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
