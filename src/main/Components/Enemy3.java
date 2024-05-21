package main.Components;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Frame.Maingame;
import main.Interface.Moveable;
import main.Service.BackgroundEnemyService2;
import main.Service.BackgroundEnemyService3;
import main.State.EnemyWay;

public class Enemy3 extends JLabel implements Moveable {

	Maingame stage;
	Player player;
	Enemy2 enemy;

	private boolean flag = true;

	private int direction;

	// 적군의 좌표값 위치 상태
	private int x;
	private int y;
	private ImageIcon enemyR, enemyL, enemyD, enemyU, enemyS;
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

	// 스크림모드의 움직임상태
	private boolean screamLeft;
	private boolean screamRight;
	private boolean screamUp;
	private boolean screamDown;

	// 적군 속도 상태
	private final int SPEED = 2; // 수정

	// enemy 스타트 시간 0.1초단위
	private final int enemyStart = 150;

	// enemy 피격 박스
	int beattackedBox = 28;

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
		enemyS = new ImageIcon("img/ghostmove/ghostDie.gif");

		backgroundEnemyService3 = new BackgroundEnemyService3(this);

		// 에너미가 가만히 멈춤 상태
		left = false;
		right = false;
		up = false;
		down = false;
		screamLeft = false;
		screamRight = false;
		screamUp = false;
		screamDown = false;

		enemyWay = EnemyWay.RIGHT;

		// 처음 실행 시 초기 값 셋팅 (수정)
		x = 350;
		y = 435;
		player = stage.getPlayer();

	}

	private void setInitLayout() {

		setIcon(enemyD);
		setSize(28, 28); // 수정 해야됨
		setLocation(x, y);

	}

	// 에너미가 공격 당하는 상태
	public void beAttcked() {

		if (player.getState() == 2) {
			setIcon(enemyS);
			screamchange();
		}
		if (stage.getPlayer().getState() == 1) {
			change();
		}
	}

	// 리스폰
	public void enemyRestart() {

		setLocation(x = 350, y = 435);
		setIcon(enemyU);
		new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < 50; i++) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 34; i++) {
					y -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();

					}
				}
				// 예외처리
				if (player.getState() == 1) {
					right();
					if(player.getState() == 1) {
						right();
						}
						else if(player.getState() == 2) {
							screamRight();
						}
				}
				else if (player.getState() == 2) {
					screamRight();
				}
			}
		}).start();
	}

	// 스크림적의 방향을 무작위로 변경하는 값
	private void screamchange() {
		Random random = new Random();
		int direction3 = random.nextInt(4); // 0~3 무작위 숫자 생성
		setIcon(enemyS);
		switch (direction3) {
		case 0:
			// 방어적 코드
			if (!leftWallCrash) {
				screamLeft();
			}
			break;
		case 1:
			// 방어적 코드
			if (!rightWallCrash) {
				screamRight();
			}
			break;
		case 2:
			// 방어적 코드
			if (!upWallCrash) {
				screamUp();
			}
			break;
		case 3:
			// 방어적 코드
			if (!downWallCrash) {
				screamDown();
			}
			break;
		default:
			// 이동할 수 있는 방향이 없는 경우
			break;
		}
	}

	// 스크림 모드 왼쪽
	public void screamLeft() {
		this.enemyWay = EnemyWay.LEFT;
		screamLeft = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (screamLeft) {
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 왼쪽 상단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamLeft = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 오른쪽상단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamLeft = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 왼쪽하단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamLeft = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 오른쪽 하단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamLeft = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					if (player.getState() == 1) {
						screamLeft = false;
						change();
					}
					if (backgroundEnemyService3.leftWall()) {
						break;
					}
				}
				if (backgroundEnemyService3.leftWall()) {
					screamLeft = false;
					screamchange();
				}
			}
		}).start();
	}

	// 스크림 모드 오른쪽
	public void screamRight() {
		this.enemyWay = EnemyWay.RIGHT;
		screamRight = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (screamRight) {
					x += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 왼쪽 상단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamRight = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 오른쪽상단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamRight = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 왼쪽하단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamRight = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 오른쪽 하단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamRight = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					if (player.getState() == 1) {
						screamRight = false;
						change();
					}
					if (backgroundEnemyService3.rightWall()) {
						break;
					}
				}
				if (backgroundEnemyService3.rightWall()) {
					screamRight = false;
					screamchange();
				}
			}
		}).start();
	}

	// 스크림 모드 위
	public void screamUp() {
		this.enemyWay = EnemyWay.UP;
		screamUp = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (screamUp) {
					y -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 왼쪽 상단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamUp = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 오른쪽상단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamUp = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 왼쪽하단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamUp = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 오른쪽 하단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamUp = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					if (backgroundEnemyService3.upWall()) {
						break;
					}
					if (player.getState() == 1) {
						screamUp = false;
						change();
					}
				}
				if (backgroundEnemyService3.upWall()) {
					screamUp = false;
					screamchange();
				}
			}
		}).start();
	}

	// 스크림 모드 아래
	public void screamDown() {
		this.enemyWay = EnemyWay.DOWN;
		screamDown = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (screamDown) {

					y += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 왼쪽 상단

					if (player.getX() == x && player.getY() == y) {
						screamDown = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 오른쪽상단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamDown = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 왼쪽하단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamDown = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					// 오른쪽 하단
					if (Math.abs((player.getX() + 28 / 2) - (x + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (y + 28 / 2)) < (28 / 2 + 28 / 2)) {
						screamDown = false;
						player.setEatedCount(player.getEatedCount()+100);
						enemyRestart();
					}
					if (backgroundEnemyService3.downWall()) {
						break;
					}
					if (player.getState() == 1) {
						screamDown = false;
						change();
					}
				}
				if (backgroundEnemyService3.downWall()) {
					screamDown = false;
					screamchange();
				}
			}
		}).start();
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
		direction = random.nextInt(4); // 0~3 무작위 숫자 생성

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
					if (player.getState() == 2) {
						left = false;
						beAttcked();
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
					if (player.getState() == 2) {
						right = false;
						beAttcked();
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
					if (player.getState() == 2) {
						up = false;
						beAttcked();
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
					if (player.getState() == 2) {
						down = false;
						beAttcked();
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

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isScreamLeft() {
		return screamLeft;
	}

	public void setScreamLeft(boolean screamLeft) {
		this.screamLeft = screamLeft;
	}

	public boolean isScreamRight() {
		return screamRight;
	}

	public void setScreamRight(boolean screamRight) {
		this.screamRight = screamRight;
	}

	public boolean isScreamUp() {
		return screamUp;
	}

	public void setScreamUp(boolean screamUp) {
		this.screamUp = screamUp;
	}

	public boolean isScreamDown() {
		return screamDown;
	}

	public void setScreamDown(boolean screamDown) {
		this.screamDown = screamDown;
	}
}