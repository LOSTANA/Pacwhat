package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Components.Enemy1;
import main.Components.Enemy2;
import main.Components.Enemy3;
import main.Components.Item;
import main.Components.Player;

public class Maingame extends JFrame {

	Maingame stage = this;

	private JLabel backgroundMap;
	private JPanel health;
	private JPanel score;
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
		return this.enemy;
	}

	public Enemy2 getEnemy2() {
		return this.enemy2;
	}

	public Enemy3 getEnemy3() {
		return enemy3;
	}

	public Item[] getItem() {
		return item;
	}

	public JLabel getBackgroundMap() {
		return backgroundMap;
	}

	public void setBackgroundMap(JLabel backgroundMap) {
		this.backgroundMap = backgroundMap;
	}

	private void initData() {
		backgroundMap = new JLabel(new ImageIcon("img/background/Background.png"));

		setSize(750, 1000);
		setContentPane(backgroundMap);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		score = new JPanel();
		score.setBackground(new Color(0,0,0));
		score.setLayout(new FlowLayout(FlowLayout.LEADING, 20,40));
		health = new JPanel();
		health.setBackground(new Color(0,0,0));
		health.setLayout(new FlowLayout(FlowLayout.TRAILING, 20,40));
		
		player = new Player(this);
		enemy = new Enemy1(this);
		enemy2 = new Enemy2(this);
		enemy3 = new Enemy3(this);

		for (int i = 0; i < 239; i++) {
			item[i] = new Item(this);
		}

	}

	private void setInitLayout() {
		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		add(score, BorderLayout.NORTH);
		add(health, BorderLayout.SOUTH);

		add(player);
		add(enemy);
		add(enemy2);
		add(enemy3);

		for (int i = 1; i < 239; i++) {

			if (i % 17 == 0) {
				if (i == 0) {
					item[i + 1].setX(item[i].getX() + 40);
					item[i + 1].setY(item[i].getY());
				} else {
					if (i + 1 < 239) {
						item[i + 1].setY(item[i].getY() + 55);
						item[i + 1].setX(item[0].getX());
					}
				}
			} else {

				if (i == 239) {

				} else {
					if (i + 1 < 239) {
						item[i + 1].setX(item[i].getX() + 40);
						item[i + 1].setY(item[i].getY());
					} else {

					}

				}

			}
			add(item[i]);
			System.out.println(i + "번째 좌표" + item[i].getX() + " , " + item[i].getY());

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

				default:

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
					System.out.println(1);
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
						for (int i = 0; i < 239; i++) {
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
