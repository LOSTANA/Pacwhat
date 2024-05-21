package main.Components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Frame.Maingame;
import main.Interface.Moveable;
import main.Service.BackgroundPlayerService;
import main.State.PlayerWay;

public class Player extends JLabel implements Moveable {

	Maingame stage;

	// 플레이어 살아있는상태 1, 죽은상태 0, 에너미를 먹을수 있는 상태 2 , 클리어 9
	private int state;
	private Item[] item;

	// 플레이어 목숨
	private int playerLife;

	// 점수
	private String score;
	// 최근점수
	private int finalscore;
	// 클리어에 필요한 점수
	private int clearScore;

	public boolean strong = false;

	private BackgroundPlayerService backgroundPlayerService;

	private int x;
	private int y;
	private int eatedCount = 0;

	// 플레이어 아이콘 움직임 배열
	private ImageIcon[] imageIconR = new ImageIcon[5];
	private ImageIcon[] imageIconL = new ImageIcon[5];
	private ImageIcon[] imageIconU = new ImageIcon[5];
	private ImageIcon[] imageIconD = new ImageIcon[5];

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

	// 플레이어 속도 상태
	private final int SPEED = 3;

	PlayerWay playerWay;

	public Player(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();
		new Thread(new BackgroundPlayerService(this)).start();
	}

	private void initData() {
		// 오른쪽 이미지 배열
		imageIconR[0] = new ImageIcon("img/pacman/pac0_R.png");
		imageIconR[1] = new ImageIcon("img/pacman/pac1_R.png");
		imageIconR[2] = new ImageIcon("img/pacman/pac2_R.png");
		imageIconR[3] = new ImageIcon("img/pacman/pac3_R.png");
		imageIconR[4] = new ImageIcon("img/pacman/pac4_R.png");

		// 왼쪽 이미지 배열
		imageIconL[0] = new ImageIcon("img/pacman/pac0_L.png");
		imageIconL[1] = new ImageIcon("img/pacman/pac1_L.png");
		imageIconL[2] = new ImageIcon("img/pacman/pac2_L.png");
		imageIconL[3] = new ImageIcon("img/pacman/pac3_L.png");
		imageIconL[4] = new ImageIcon("img/pacman/pac4_L.png");

		// 위쪽 이미지 배열
		imageIconU[0] = new ImageIcon("img/pacman/pac0_U.png");
		imageIconU[1] = new ImageIcon("img/pacman/pac1_U.png");
		imageIconU[2] = new ImageIcon("img/pacman/pac2_U.png");
		imageIconU[3] = new ImageIcon("img/pacman/pac3_U.png");
		imageIconU[4] = new ImageIcon("img/pacman/pac4_U.png");

		// 아래쪽 이미지 배열
		imageIconD[0] = new ImageIcon("img/pacman/pac0_D.png");
		imageIconD[1] = new ImageIcon("img/pacman/pac1_D.png");
		imageIconD[2] = new ImageIcon("img/pacman/pac2_D.png");
		imageIconD[3] = new ImageIcon("img/pacman/pac3_D.png");
		imageIconD[4] = new ImageIcon("img/pacman/pac4_D.png");

		// 초기위치값
		x = 355;
		y = 520;

		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;
		topWallCrash = false;
		bottomWallCrash = false;

		state = 1; // 살아있는 상태

		playerLife = 3; // 목숨 3개

		eatedCount = 0; // 점수 0점

		clearScore = 1600; // 클리어에 필요한 점수

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Maingame getStage() {
		return stage;
	}

	public void setStage(Maingame stage) {
		this.stage = stage;
	}

	public Item[] getItem() {
		return item;
	}

	public void setItem(Item[] item) {
		this.item = item;
	}

	public int getPlayerLife() {
		return playerLife;
	}

	public void setPlayerLife(int playerLife) {
		this.playerLife = playerLife;
	}

	public int getEatedCount() {
		return eatedCount;
	}

	public void setEatedCount(int eatedCount) {
		this.eatedCount = eatedCount;
	}

	public ImageIcon[] getImageIconU() {
		return imageIconU;
	}

	public void setImageIconU(ImageIcon[] imageIconU) {
		this.imageIconU = imageIconU;
	}

	public ImageIcon[] getImageIconD() {
		return imageIconD;
	}

	public void setImageIconD(ImageIcon[] imageIconD) {
		this.imageIconD = imageIconD;
	}

	public ImageIcon[] getImageIconR() {
		return imageIconR;
	}

	public void setImageIconR(ImageIcon[] imageIconR) {
		this.imageIconR = imageIconR;
	}

	public ImageIcon[] getImageIconL() {
		return imageIconL;
	}

	public void setImageIconL(ImageIcon[] imageIconL) {
		this.imageIconL = imageIconL;
	}

	public int getFinalscore() {
		return finalscore;
	}

	public void setFinalscore(int finalscore) {
		this.finalscore = finalscore;
	}

	// 오른쪽으로 입 벌렸다가 닫음
	public void changeIconRight() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				while (right) {
					for (int i = 0; i < 5; i++) {
						if (right == false) {
							break;
						}
						setIcon(imageIconR[i]);
						sleep(2);
					}

					for (int i = 5; i > 0; i--) {
						if (right == false) {
							break;
						}
						setIcon(imageIconR[i - 1]);
						sleep(2);
					}

				}
			}
		}).start();
	}

	// 왼쪽으로 입 벌렸다가 닫음
	public void changeIconLeft() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				while (left) {
					for (int i = 0; i < 5; i++) {
						setIcon(imageIconL[i]);
						if (left == false) {
							break;
						}
						sleep(2);
					}

					for (int i = 5; i > 0; i--) {
						setIcon(imageIconL[i - 1]);
						if (left == false) {
							break;
						}
						sleep(2);
					}
				}
			}
		}).start();
	}

	// 위쪽으로 입 벌렸다가 닫음
	public void changeIconUp() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				while (up) {
					for (int i = 0; i < 5; i++) {
						setIcon(imageIconU[i]);
						if (up == false) {
							break;
						}
						sleep(2);
					}

					for (int i = 5; i > 0; i--) {
						setIcon(imageIconU[i - 1]);
						if (up == false) {
							break;
						}
						sleep(2);
					}
				}
			}
		}).start();
	}

	// 아래쪽으로 입 벌렸다가 닫음
	public void changeIconDown() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				while (down) {
					for (int i = 0; i < 5; i++) {
						setIcon(imageIconD[i]);
						if (down == false) {
							break;
						}
						sleep(2);
					}

					for (int i = 5; i > 0; i--) {
						setIcon(imageIconD[i - 1]);
						if (down == false) {
							break;
						}
						sleep(2);
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
					bridgeLeft();
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					eated();
				}
			}
		}).start();
		setRight(false);
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
					bridgeRight();
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					eated();
				}
			}
		}).start();
		setLeft(false);
	}

	@Override
	synchronized public void up() {
		playerWay = PlayerWay.UP;
		up = true;
		changeIconUp();

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
					eated();
				}
			}
		}).start();
	}

	@Override
	synchronized public void down() {
		playerWay = PlayerWay.DOWN;
		down = true;
		changeIconDown();

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
					eated();
				}
			}
		}).start();
	}

	// 플레이어 완전히 죽었을때 ( life -> 0)
	// state 1 -- > 0
	public void completelyDie() {

		state = 0;
		stage.getPlayer().setIcon(null);
		stage.healthScreen[1].setIcon(null);
		stage.remove(stage.getPlayer());
		stage.healthScreen[0].setText("----- YOU DIE -----");
		sleep(50);
		finalscore = eatedCount;
		stage.start(stage);
		stage.dispose();
		eatedCount = 0;
	}

	public void lostLifeMotion() {
		if (state == 0) {
			for (int i = 0; i <= 3; i++) {
				setIcon(imageIconR[2]);
				stage.healthScreen[playerLife + 1].setIcon(imageIconR[4]);
				sleep(20);
				setIcon(null);
				stage.healthScreen[playerLife + 1].setIcon(null);
				sleep(20);
			}
			setIcon(imageIconR[2]);
		}
	}

	// 플레이어 에너미1랑 부딪힐 경우
	public void isBeAttacked1() {
		int absXResult = Math.abs(x - stage.getEnemy().getX());
		int absYResult = Math.abs(y - stage.getEnemy().getY());
		if (absXResult < 35 && absYResult < 35) {
			this.state = 0;
			playerLife--;
			sleep(20);
			if (stage.getPlayer().getState() == 0) {
				if (playerLife != 0) {
					lostLifeMotion();
				} else {
					completelyDie();
				}
				sleep(120);
			}
			state = 1;
		}
	}

	// 플레이어 에너미2랑 부딪힐 경우
	public void isBeAttacked2() {
		int absXResult = Math.abs(x - stage.getEnemy2().getX());
		int absYResult = Math.abs(y - stage.getEnemy2().getY());
		if (absXResult < 35 && absYResult < 35) {
			this.state = 0;
			playerLife--;
			sleep(20);
			if (stage.getPlayer().getState() == 0) {
				if (playerLife != 0) {
					lostLifeMotion();
				} else {
					completelyDie();
				}
				sleep(120);
			}
			state = 1;
		}
	}

	// 플레이어 에너미3랑 부딪힐 경우
	public void isBeAttacked3() {
		int absXResult = Math.abs(x - stage.getEnemy3().getX());
		int absYResult = Math.abs(y - stage.getEnemy3().getY());
		if (absXResult < 35 && absYResult < 35) {
			this.state = 0;
			playerLife--;
			sleep(20);
			if (stage.getPlayer().getState() == 0) {
				if (playerLife != 0) {
					lostLifeMotion();
				} else {
					completelyDie();
				}
				sleep(120);
			}
			state = 1;
		}
	}

	// 통로 넘어가기 왼쪽
	public void bridgeLeft() {
		if (x <= 10 && (y <= 470 && y >= 430)) {
			x = 680;
			y = 450;
			setLocation(x, y);
		}
	}

	// 통로 넘어가기 오른쪽
	public void bridgeRight() {
		if (x >= 690 && (y <= 470 && y >= 430)) {
			x = 20;
			y = 450;
			setLocation(x, y);
		}
	}

	// 클리어 스테이지
	public void clearStage() {

		left = false;
		right = false;
		up = false;
		down = false;
		state = 9;
		stage.scoreScreen.setText("--- Clear!!! ---");
		for (int i = 0; i < 239; i++) {
			stage.getItem()[i].setIcon(null);
		}
		finalscore = eatedCount;
		sleep(50);
		stage.dispose();
		stage.start(stage);
	}

	// 먹기 구현
	public void eated() {
		for (int i = 0; i < 239; i++) {
			int absXResult = Math.abs(x - stage.getItem()[i].getX());
			int absYResult = Math.abs(y - stage.getItem()[i].getY());

			if (absXResult < 23 && absYResult < 23 && stage.getItem()[i].getState() == 0) {
				stage.getItem()[i].setIcon(null);
				stage.getItem()[i].setState(1);
				eatedCount += 10;
			} else if (absXResult < 23 && absYResult < 23 && stage.getItem()[i].getState() == 2) {
				if (this.state == 2 && strong == false) {
					eatedCount += 50;
					stage.getItem()[i].setIcon(null);
					stage.getItem()[i].setState(1);
					strong = true;
					return;
				} else {
					stage.getItem()[i].setIcon(null);
					stage.getItem()[i].setState(1);
					state = 2;
					eatedCount += 50;
				}
				stage.getItem()[i].setIcon(null);
				stage.getItem()[i].setState(1);
			}
			score = Integer.toString(eatedCount);
			stage.scoreScreen.setText("점수 : " + eatedCount);
			if (eatedCount >= clearScore) {
				clearStage();
				break;
			}
		}
	}

	// Thread.sleep 지우기
	// sleep 1 에 0.01초
	public void sleep(int a) {
		for (int i = 0; i < a; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}