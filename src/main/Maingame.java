package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Components.Enemy1;
import main.Components.Enemy2;
import main.Components.Enemy3;
import main.Components.Item;
import main.Components.Player;
import main.Service.BackgroundPlayerService2;
import main.Service.BackgroundPlayerService3;

public class Maingame extends JFrame {

	Maingame stage = this;
	Random rd = new Random();
	private JLabel backgroundMap;
	private JPanel health;
	private JPanel score;
	public JLabel scoreScreen;
	public JLabel[] healthScreen = new JLabel[4];
	public Image heal;
	private Player player;
	private Enemy1 enemy;
	private Enemy2 enemy2;
	private Enemy3 enemy3;
	
	private Thread back2;
	private Thread back3;

	public int width = 0;
	public int height = 0;

	private Item[] item = new Item[239];
	

	public Maingame() {
		initData();
		setInitLayout();
		addEventListener();
		backToNormal();

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

	public JLabel getScoreScreen() {
		return scoreScreen;
	}

	public void setScoreScreen(JLabel scoreScreen) {
		this.scoreScreen = scoreScreen;
	}

	public JLabel[] getHealthScreen() {
		return healthScreen;
	}

	public void setHealthScreen(JLabel[] healthScreen) {
		this.healthScreen = healthScreen;
	}

	private void initData() {
		backgroundMap = new JLabel(new ImageIcon("img/background/Background.png"));

		setSize(750, 1000);
		setContentPane(backgroundMap);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		score = new JPanel();
		score.setBackground(new Color(0, 0, 0));
		score.setBounds(0, 0, 750, 77);

		health = new JPanel();
		health.setBackground(new Color(0, 0, 0));
		health.setBounds(0, 885, 750, 80);

		player = new Player(this);
		enemy = new Enemy1(this);
		enemy2 = new Enemy2(this);
		enemy3 = new Enemy3(this);

		for (int i = 0; i < 239; i++) {
			item[i] = new Item(this);
		}

		scoreScreen = new JLabel();
		scoreScreen.setText("점수 : 0");
		scoreScreen.setFont(new Font("DungGeunMo", Font.BOLD, 38));
		scoreScreen.setForeground(Color.WHITE);

		healthScreen[0] = new JLabel();
		healthScreen[0].setText("목숨 : ");
		healthScreen[0].setFont(new Font("DungGeunMo", Font.BOLD, 38));
		healthScreen[0].setForeground(Color.WHITE);
		
		// 플레이어 충돌 감지기
			
				

		for (int i = 1; i < 4; i++) {

			healthScreen[i] = new JLabel(new ImageIcon("img/pacman/pac4_R.png"));
			healthScreen[i].setSize(28, 28);
			healthScreen[i].setLocation(width, height);
			width += 30;


			
		}

	}
	
	
	
	
	private void setInitLayout() {

		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);

		add(score);
		add(health);
		score.add(scoreScreen);
		for (int i = 0; i < 4; i++) {

			health.add(healthScreen[i]);

		}
		add(player);
		add(enemy);
		add(enemy2);
		add(enemy3);
		add(new JLabel("테스트"));

		for (int i = 1; i < 239; i++) {

			if (i % 17 == 0) {
				if (i == 0) {
					item[i + 1].setX(item[i].getX() + 40);
					item[i + 1].setY(item[i].getY());
				} else {
					if (i + 1 < 239) {
						item[i + 1].setY(item[i].getY() + 56);
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

		}

		setVisible(true);
		for (int i = 0; i < 4; i++) {
			int a = rd.nextInt(238) + 1;
			if (item[a].getIcon() == null) {
				i--;
				System.out.println(i + 1 + "롤백");
			} else if (item[a].getIcon() == item[a].getCoin()) {

				item[a].transitem(i);
				System.out.println(a + "번째에 아이템 " + i + "생성");

			} else {
				
			}

		}
		
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
					pause();
					enemy.setLeft(false);
					enemy.setRight(false);
					enemy.setUp(false);
					enemy.setDown(false);
					enemy2.setLeft(false);
					enemy2.setRight(false);
					enemy2.setUp(false);
					enemy2.setDown(false);
					enemy3.setLeft(false);
					enemy3.setRight(false);
					enemy3.setUp(false);
					enemy3.setDown(false);
					player.setLeft(false);
					player.setRight(false);
					player.setUp(false);
					player.setDown(false);

					if (enemy.isLeft() == true) {
						enemy.setLeft(false);
					} else if (enemy.isLeft() == true) {
						enemy.setRight(false);
					} else if (enemy.isUp() == true) {
						enemy.setUp(false);
					} else if (enemy.isDown() == true) {
						enemy.setDown(false);
					}

					if (enemy2.isLeft() == true) {
						enemy2.setLeft(false);
					} else if (enemy2.isLeft() == true) {
						enemy2.setRight(false);
					} else if (enemy2.isUp() == true) {
						enemy2.setUp(false);
					} else if (enemy2.isDown() == true) {
						enemy2.setDown(false);
					}

					if (enemy3.isLeft() == true) {
						enemy3.setLeft(false);
					} else if (enemy3.isLeft() == true) {
						enemy3.setRight(false);
					} else if (enemy3.isUp() == true) {
						enemy3.setUp(false);
					} else if (enemy3.isDown() == true) {
						enemy3.setDown(false);
					}
					break;

				case KeyEvent.VK_1:
					System.out.println("좌표 x : " + player.getX() + ", 좌표 y : " + player.getY());
					break;
				case KeyEvent.VK_ESCAPE:

					System.exit(0);
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

		back2 = new Thread(new BackgroundPlayerService2(player, enemy, enemy2, enemy3));
	    back3 = new Thread(new BackgroundPlayerService3(player, enemy, enemy2, enemy3));	
		
	   
	}
	
	 public void playerAttackable() {

			player.setState(2);
			if(player.getState()==2) {
				System.out.println("어태커블 들어옴");
				if(back2 != null) {
					System.out.println("어태커블 걸러짐");
					try {
						back2.interrupt();
					} catch (Exception e) {
						// TODO: handle exception
					}
				if(back2.interrupted()) {
				System.out.println("어태커블 작동");
				}
				System.out.println("어태커블 -back3 시작");
				back3.start();
			}
				else {
					System.out.println(player.getState());
				}
			}
			if(player.getState()==1) {
				System.out.println("어태커블로 돌아옴");
				back3.stop();
				System.out.println("백투노말 고");
				backToNormal();
			}
		}
		
		public void backToNormal() {
			if(player.getState()!=2) {
				if(back3 !=null) {
					System.out.println("노말 걸러짐");
					back3.interrupt();
				if(back3.interrupted()) {
					System.out.println("백투노말 작동");
				}
				
				System.out.println("백투노말 -back2 시작");
				back2.start();
				System.out.println("백2 시작");
				}
				}
			if(player.getState()==2) {
				back2.stop();
				System.out.println("어태커블 고");
				playerAttackable();
			}
			}
	

	public void pause() {
		new PauseGame(this);
	}
	public void start(Maingame stage) {
		this.stage = stage;
		new Startgame(this);
		dispose();
	}


	

}