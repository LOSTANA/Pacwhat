package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	Maingame stage;
	
	private int x;
	private int y;
	private ImageIcon[] imageIconR = new ImageIcon[5]; 
	private ImageIcon[] imageIconL = new ImageIcon[5]; 
	
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
		imageIconR[0] = new ImageIcon("img/pacman/pac0_R.png");
		imageIconR[1] = new ImageIcon("img/pacman/pac1_R.png");
		imageIconR[2] = new ImageIcon("img/pacman/pac2_R.png");
		imageIconR[3] = new ImageIcon("img/pacman/pac3_R.png");
		imageIconR[4] = new ImageIcon("img/pacman/pac4_R.png");
		
		imageIconL[0] = new ImageIcon("img/pacman/pac0_L.png");
		imageIconL[1] = new ImageIcon("img/pacman/pac1_L.png");
		imageIconL[2] = new ImageIcon("img/pacman/pac2_L.png");
		imageIconL[3] = new ImageIcon("img/pacman/pac3_L.png");
		imageIconL[4] = new ImageIcon("img/pacman/pac4_L.png");
		
		// 초기위치값 임시로 설정 -- 추후 수정예정
		x = 355;
		y = 445;

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
		setIcon(imageIconR[0]);
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
		return imageIconR[0];
	}

	public void setPlayer0(ImageIcon player0) {
		this.imageIconR[0] = player0;
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
	
	public synchronized void changeIconRight() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(right) {
					for(int i = 0; i < 5; i++) {
						setIcon(imageIconR[i]);
						try {
							Thread.sleep(120);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					for(int i = 5; i > 0; i--) {
						setIcon(imageIconR[i-1]);
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
	
	public synchronized void changeIconLeft() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(left) {
					for(int i = 0; i < 5; i++) {
						setIcon(imageIconL[i]);
						try {
							Thread.sleep(120);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					for(int i = 5; i > 0; i--) {
						setIcon(imageIconL[i-1]);
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
	synchronized public void left() {
		playerWay = PlayerWay.LEFT;
		left = true;
		changeIconLeft();
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
	synchronized public void right() {
		playerWay = PlayerWay.RIGHT;
		right = true;
		changeIconRight();
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