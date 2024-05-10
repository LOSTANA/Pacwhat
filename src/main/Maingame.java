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

//		noteated();

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
//	public void noteated() { // 임시코드 (정말 최후의 수단)
//		item[9].setState(1);
//		item[9].setIcon(null);
//		item[22].setState(1);
//		item[22].setIcon(null);
//		item[26].setState(1);
//		item[26].setIcon(null);
//		item[28].setState(1);
//		item[28].setIcon(null);
//		item[56].setState(1);
//		item[56].setIcon(null);
//		item[64].setState(1);
//		item[64].setIcon(null);
//		item[73].setState(1);
//		item[73].setIcon(null);
//		item[77].setState(1);
//		item[77].setIcon(null);
//		item[79].setState(1);
//		item[79].setIcon(null);
//		item[90].setState(1);
//		item[90].setIcon(null);
//		item[98].setState(1);
//		item[98].setIcon(null);
//		item[109].setState(1);
//		item[109].setIcon(null);
//		item[124].setState(1);
//		item[124].setIcon(null);
//		item[126].setState(1);
//		item[126].setIcon(null);
//		item[132].setState(1);
//		item[132].setIcon(null);
//		item[134].setState(1);
//		item[134].setIcon(null);
//		item[141].setState(1);
//		item[141].setIcon(null);
//		item[143].setState(1);
//		item[143].setIcon(null);
//		item[144].setState(1);
//		item[144].setIcon(null);
//		item[145].setState(1);
//		item[145].setIcon(null);
//		item[146].setState(1);
//		item[146].setIcon(null);
//		item[147].setState(1);
//		item[147].setIcon(null);
//		item[149].setState(1);
//		item[149].setIcon(null);
//		item[151].setState(1);
//		item[151].setIcon(null);
//		item[162].setState(1);
//		item[162].setIcon(null);
//		item[175].setState(1);
//		item[175].setIcon(null);
//		item[179].setState(1);
//		item[179].setIcon(null);
//		item[181].setState(1);
//		item[181].setIcon(null);
//		item[185].setState(1);
//		item[185].setIcon(null);
//		item[188].setState(1);
//		item[188].setIcon(null);
//		item[190].setState(1);
//		item[190].setIcon(null);
//		item[192].setState(1);
//		item[192].setIcon(null);
//		item[194].setState(1);
//		item[194].setIcon(null);
//		item[195].setState(1);
//		item[195].setIcon(null);
//		item[196].setState(1);
//		item[196].setIcon(null);
//		item[197].setState(1);
//		item[197].setIcon(null);
//		item[198].setState(1);
//		item[198].setIcon(null);
//		item[200].setState(1);
//		item[200].setIcon(null);
//		item[202].setState(1);
//		item[202].setIcon(null);
//		item[204].setState(1);
//		item[204].setIcon(null);
//		item[209].setState(1);
//		item[209].setIcon(null);
//		item[213].setState(1);
//		item[213].setIcon(null);
//		item[217].setState(1);
//		item[217].setIcon(null);
//		
//	}


	public static void main(String[] args) {
		new Startgame();

	}

}
