package main.Components;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Maingame;
import main.Interface.Moveable;
import main.Service.BackgroundEnemyService2;
import main.Service.BackgroundEnemyService3;
import main.State.EnemyWay;

public class Enemy3 extends JLabel implements Moveable {

	Maingame stage;

	
	// 적군의 좌표값 위치 상태
	private int x;
	private int y;
	private ImageIcon enemyR, enemyL, enemyD, enemyU;
	private BackgroundEnemyService3 backgroundEnemyService3;

	// 움직임의 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 적군 방향 상태
	EnemyWay enemyWay;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean upWallCrash;
	private boolean downWallCrash;

	// 적군 속도 상태
	private final int SPEED = 2; // 수정

	// enemy 스타트 시간 0.1초단위
	private final int enemyStart = 150;

	public Enemy3(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();

		enemyStart();
	}

	private void initData() {

		enemyR = new ImageIcon("img/ghostmove/redR.gif");
		enemyL = new ImageIcon("img/ghostmove/redL.gif");
		enemyD = new ImageIcon("img/ghostmove/redD.gif");
		enemyU = new ImageIcon("img/ghostmove/redU.gif");

		backgroundEnemyService3 = new BackgroundEnemyService3(this);

		// 에너미가 가만히 멈춤 상태
		left = false;
		right = false;
		up = false;
		down = false;

		enemyWay = EnemyWay.RIGHT;

		// 처음 실행 시 초기 값 셋팅 (수정)
		x = 365;
		y = 435;

	}

	private void setInitLayout() {

		setIcon(enemyU);
		setSize(28, 28); // 수정 해야됨
		setLocation(x, y);

	}

	// 에너미 스타트
	public void enemyStart() {

		new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < enemyStart; i++) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 35; i++) {
					// 적을 위로 50만큼 이동
					y -= SPEED;
					setLocation(x, y);

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				right();
			}
		}).start();

	}

	// 적의 방향을 무작위로 변경하는 값
	private void change() {
		Random random = new Random();
		int direction = random.nextInt(4); // 0~3 무작위 숫자 생성

		switch (direction) {
		case 0:
			// 방어적 코드
			if (!leftWallCrash) {
				left();
			}
			break;
		case 1:
			// 방어적 코드
			if (!rightWallCrash) {
				right();
			}
			break;
		case 2:
			// 방어적 코드
			if (!upWallCrash) {
				up();
			}
			break;
		case 3:
			// 방어적 코드
			if (!downWallCrash) {
				down();
			}
			break;
		default:
			// 이동할 수 있는 방향이 없는 경우
			break;
		}
	}

	@Override
	public void left() {
		this.enemyWay = EnemyWay.LEFT;
		left = true;
		setIcon(enemyL);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (backgroundEnemyService3.leftWall()) {
						break;
					}
				}
				if (backgroundEnemyService3.leftWall()) {
					left = false;
					change();
				}
			}
		}).start();
	}

	@Override
	public void right() {
		this.enemyWay = EnemyWay.RIGHT;
		right = true;
		setIcon(enemyR);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (right) {
					x += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (backgroundEnemyService3.rightWall()) {
						break;
					}
				}
				if (backgroundEnemyService3.rightWall()) {
					right = false;
					change();
				}
			}
		}).start();
	}

	@Override
	public void up() {
		this.enemyWay = EnemyWay.UP;
		up = true;
		setIcon(enemyU);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (up) {
					y -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (backgroundEnemyService3.upWall()) {
						break;
					}
				}
				if (backgroundEnemyService3.upWall()) {
					up = false;
					change();
				}
			}
		}).start();
	}

	@Override
	public void down() {
		this.enemyWay = EnemyWay.DOWN;
		down = true;
		setIcon(enemyD);
		if (backgroundEnemyService3.downWall()) {
			down = false;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (down) {
					y += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (backgroundEnemyService3.downWall()) {
						break;
					}
				}
				if (backgroundEnemyService3.downWall()) {
					down = false;
					change();
				}

			}
		}).start();

	}

	// get,set
	public Maingame getmContext() {
		return stage;
	}

	public void setStage(Maingame stage) {
		this.stage = stage;
	}

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

	public ImageIcon getEnemyR() {
		return enemyR;
	}

	public void setEnemyR(ImageIcon enemyR) {
		this.enemyR = enemyR;
	}

	public ImageIcon getEnemyL() {
		return enemyL;
	}

	public void setEnemyL(ImageIcon enemyL) {
		this.enemyL = enemyL;
	}

	public ImageIcon getEnemyD() {
		return enemyD;
	}

	public void setEnemyD(ImageIcon enemyD) {
		this.enemyD = enemyD;
	}

	public ImageIcon getEnemyU() {
		return enemyU;
	}

	public void setEnemyU(ImageIcon enemyU) {
		this.enemyU = enemyU;
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

	public EnemyWay getEnemyWay() {
		return enemyWay;
	}

	public void setEnemyWay(EnemyWay enemyWay) {
		this.enemyWay = enemyWay;
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

	public boolean isUpWallCrash() {
		return upWallCrash;
	}

	public void setUpWallCrash(boolean upWallCrash) {
		this.upWallCrash = upWallCrash;
	}

	public boolean isDownWallCrash() {
		return downWallCrash;
	}

	public void setDownWallCrash(boolean downWallCrash) {
		this.downWallCrash = downWallCrash;
	}

	public int getSPEED() {
		return SPEED;
	}

}
