package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	Maingame stage;

	
	private int x;
	private int y;
	private ImageIcon player0, player1, player2, player3, player4; 
	
	// 플레이어 움직임
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean topWallCrash;
	private boolean bottomWallCrash;

	// 플레이어 속도 상태 -- 추후 수정
	private final int SPEED = 3;

	PlayerWay playerWay;
	
	public Player(Maingame stage) {
		this.stage = stage;


		initData();
		setInitLayout();
		
		new Thread(new BackgroundPlayerService(this)).start();
		
	}


	private void initData() {
		player0 = new ImageIcon("img/pacman/pac0.png");
		player1 = new ImageIcon("img/pacman/pac1.png");
		player2 = new ImageIcon("img/pacman/pac2.png");
		player3 = new ImageIcon("img/pacman/pac3.png");
		player4 = new ImageIcon("img/pacman/pac4.png");

		// 초기위치값 추후 수정
		x = 375;
		y = 425;

		left = false;
		right = false;
		up = false;
		down = false;

		playerWay = PlayerWay.RIGHT;
	}
	
	private void setInitLayout() {
		setIcon(player0);
		setSize(28, 28);
		setLocation(x, y);
	}
	
	// getter, setter
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ImageIcon getPlayer0() {
		return player0;
	}

	public void setPlayer0(ImageIcon player0) {
		this.player0 = player0;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int getSPEED() {
		return SPEED;
	}
	
	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public boolean isTopWallCrash() {
		return topWallCrash;
	}

	public void setTopWallCrash(boolean topWallCrash) {
		this.topWallCrash = topWallCrash;
	}

	public boolean isBottomWallCrash() {
		return bottomWallCrash;
	}

	public void setBottomWallCrash(boolean bottomWallCrash) {
		this.bottomWallCrash = bottomWallCrash;
	}

	@Override
	public void left() {
		playerWay = PlayerWay.LEFT;
		left = true;
		setIcon(player0);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					x = x - SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void right() {
		playerWay = PlayerWay.RIGHT;
		right = true;
		setIcon(player0);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (right) {
					x = x + SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void up() {
		playerWay = PlayerWay.UP;
		up = true;
		setIcon(player0);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (up) {
					y = y - SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void down() {
		playerWay = PlayerWay.DOWN;
		down = true;
		setIcon(player0);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (down) {
					y = y + SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}



}
