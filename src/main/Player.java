package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	Maingame stage;

	
	private int x;
	private int y;
	private ImageIcon[] imageIcon = new ImageIcon[5]; 
	// player0, player1, player2, player3, player4
	
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
		changeIcon();
	}


	private void initData() {
		imageIcon[0] = new ImageIcon("img/pacman/pac0.png");
		imageIcon[1] = new ImageIcon("img/pacman/pac1.png");
		imageIcon[2] = new ImageIcon("img/pacman/pac2.png");
		imageIcon[3] = new ImageIcon("img/pacman/pac3.png");
		imageIcon[4] = new ImageIcon("img/pacman/pac4.png");
		
		// 초기위치값 추후 수정
		x = 395;
		y = 455;

		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;
		topWallCrash = false;
		bottomWallCrash = false;

		playerWay = PlayerWay.RIGHT;
	}
	
	private void setInitLayout() {
		setIcon(imageIcon[0]);
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
		return imageIcon[0];
	}

	public void setPlayer0(ImageIcon player0) {
		this.imageIcon[0] = player0;
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

	public PlayerWay getPlayerWay() {
		return playerWay;
	}

	public void setPlayerWay(PlayerWay playerWay) {
		this.playerWay = playerWay;
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
	
	public void changeIcon() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					for(int i = 0; i < 5; i++) {
						setIcon(imageIcon[i]);
						try {
							Thread.sleep(120);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					for(int i = 5; i > 0; i--) {
						setIcon(imageIcon[i-1]);
						try {
							Thread.sleep(120);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
				
			}
		}).start();
	}
		
	
	@Override
	public void left() {
		playerWay = PlayerWay.LEFT;
		left = true;
		setIcon(imageIcon[0]);

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
		setIcon(imageIcon[0]);

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
		setIcon(imageIcon[0]);

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
		setIcon(imageIcon[0]);

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
