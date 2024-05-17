package main.Components;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Maingame;
import main.Interface.Moveable;
import main.Service.BackgroundEnemyService;
import main.State.EnemyWay;

public class Enemy1 extends JLabel implements Moveable {

	Maingame stage;
	Player player;
	Enemy1 enemy;

	private int direction;

	// 적군의 좌표값 위치 상태
	private int x;
	private int y;

	public BackgroundEnemyService getBackgroundEnemyService() {
		return backgroundEnemyService;
	}

	public void setBackgroundEnemyService(BackgroundEnemyService backgroundEnemyService) {
		this.backgroundEnemyService = backgroundEnemyService;
	}

	public Maingame getStage() {
		return stage;
	}

	private ImageIcon enemyR, enemyL, enemyD, enemyU, enemyS, enemyT;
	private BackgroundEnemyService backgroundEnemyService;

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

	
	private int state = 1;
	// 적군 속도 상태
	private final int SPEED = 2; // 수정

	// enemy 스타트 시간 0.1초단위
	private final int enemyStart = 50;

	// enemy 피격 박스
	int beattackedBox = 28;

	public Enemy1(Maingame stage) {
		this.stage = stage;
		this.player = stage.getPlayer();
		
		initData();
		setInitLayout();

		enemyStart();
	}

	private void initData() {

		enemyR = new ImageIcon("img/ghostmove/cyanR.gif");
		enemyL = new ImageIcon("img/ghostmove/cyanL.gif");
		enemyD = new ImageIcon("img/ghostmove/cyanD.gif");
		enemyU = new ImageIcon("img/ghostmove/cyanU.gif");
		enemyS = new ImageIcon("img/ghost1/ghostDie2.gif");
		enemyT = new ImageIcon("img/ghost1/ghostDieTime.gif");

		backgroundEnemyService = new BackgroundEnemyService(this);

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
		x = 360;
		y = 435;

	}

	private void setInitLayout() {

		setIcon(enemyD);
		setSize(28, 28); // 수정 해야됨
		setLocation(x, y);

	}

	// 에너미가 공격 당하는 상태

	// 리스폰
	private void enemyRestart() {

		setLocation(x = 360, y = 435);
		setIcon(enemyU);
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 35; i++) {
					y -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();

					}
					right();
				}
			}
		}).start();
	}

	// 스크림적의 방향을 무작위로 변경하는 값
	private void screamchange() {
		Random random = new Random();
		direction = random.nextInt(4); // 0~3 무작위 숫자 생성

		switch (direction) {
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
	private void screamLeft() {
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
					if (stage.getPlayer().getX() == x && stage.getPlayer().getY() == y) {
						enemyRestart();
					}
					// 오른쪽상단
					if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
							&& stage.getPlayer().getY() == y) {
						enemyRestart();
					}
					// 왼쪽하단
					if (stage.getPlayer().getX() == x
							&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
						enemyRestart();
					}
					// 오른쪽 하단
					if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
							&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {

						enemyRestart();
					}
					if (stage.getPlayer().getState() == 1) {
						screamLeft = false;
						beAttcked();
					}
					if (backgroundEnemyService.leftWall()) {
						break;
					}
				}
				if (backgroundEnemyService.leftWall()) {
					screamLeft = false;
					screamchange();
				}
			}
		}).start();
	}

	// 스크림 모드 오른쪽
	private void screamRight() {
		this.enemyWay = EnemyWay.RIGHT;
		screamRight = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (screamRight) {
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 왼쪽 상단
					if (stage.getPlayer().getX() == x && stage.getPlayer().getY() == y) {
						enemyRestart();
					}
					// 오른쪽상단
					if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
							&& stage.getPlayer().getY() == y) {
						enemyRestart();
					}
					// 왼쪽하단
					if (stage.getPlayer().getX() == x
							&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
						enemyRestart();
					}
					// 오른쪽 하단
					if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
							&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
						enemyRestart();
					}
					if (stage.getPlayer().getState() == 1) {
						screamRight = false;
						beAttcked();
					}
					if (backgroundEnemyService.rightWall()) {
						break;
					}
				}
				if (backgroundEnemyService.rightWall()) {
					screamRight = false;
					screamchange();
				}
			}
		}).start();
	}

	// 스크림 모드 위
	private void screamUp() {
		this.enemyWay = EnemyWay.UP;
		screamUp = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (screamUp) {
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 왼쪽 상단
					if (stage.getPlayer().getX() == x && player.getY() == y) {
						enemyRestart();
					}
					// 오른쪽상단
					if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
							&& stage.getPlayer().getY() == y) {
						enemyRestart();
					}
					// 왼쪽하단
					if (stage.getPlayer().getX() == x
							&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
						enemyRestart();
					}
					// 오른쪽 하단
					if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
							&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
						enemyRestart();
					}
					if (backgroundEnemyService.upWall()) {
						break;
					}
					if (stage.getPlayer().getState() == 1) {
						screamUp = false;
						beAttcked();
					}
				}
				if (backgroundEnemyService.upWall()) {
					screamUp = false;
					screamchange();
				}
			}
		}).start();
	}

	// 스크림 모드 아래
	private void screamDown() {
		this.enemyWay = EnemyWay.DOWN;
		screamDown = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (screamDown) {
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 왼쪽 상단
					if (stage.getPlayer().getX() == x && stage.getPlayer().getY() == y) {
						enemyRestart();
					}
					// 오른쪽상단
					if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
							&& stage.getPlayer().getY() == y) {
						enemyRestart();
					}
					// 왼쪽하단
					if (stage.getPlayer().getX() == x
							&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
						enemyRestart();
					}
					// 오른쪽 하단
					if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
							&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
						enemyRestart();
					}
					if (backgroundEnemyService.downWall()) {
						break;
					}
					if (stage.getPlayer().getState() == 1) {
						screamDown = false;
						beAttcked();
					}
				}
				if (backgroundEnemyService.downWall()) {
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
			if (stage.getPlayer().getState() == 1) {
				if (!leftWallCrash) {
					left();
				}
			} else if (stage.getPlayer().getState() == 2) {
				if (!leftWallCrash) {
					screamLeft();
				}
			}
			break;
		case 1:
			// 방어적 코드
			if (stage.getPlayer().getState() == 1) {
				if (!rightWallCrash) {
					right();
				}
			} else if (stage.getPlayer().getState() == 2) {
				if (!leftWallCrash) {
					screamRight();
				}
			}
			break;
		case 2:
			// 방어적 코드
			if (stage.getPlayer().getState() == 1) {
				if (!upWallCrash) {
					up();
				}
			} else if (stage.getPlayer().getState() == 2) {
				if (!upWallCrash) {
					screamUp();
				}
			}

			break;
		case 3:
			// 방어적 코드
			if (stage.getPlayer().getState() == 1) {
				if (!downWallCrash) {
					down();
				}
			} else if (stage.getPlayer().getState() == 2) {
				if (!downWallCrash) {
					screamDown();
				}
			}

			break;
		default:
			// 이동할 수 있는 방향이 없는 경우
			break;
		}
	}

	@Override
	public void left() {

		if (state != 2) {
			this.enemyWay = EnemyWay.LEFT;
			left = true;
			setIcon(enemyR);

			new Thread(new Runnable() {
				@Override
				public void run() {
					while (right) {
						y -= SPEED;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (stage.getPlayer().getState() == 2) {
							left = false;
							beAttcked();
						}
						if (backgroundEnemyService.leftWall()) {
							break;
						}
					}
					if (backgroundEnemyService.leftWall()) {
						left = false;
						change();
					}
				}
			}).start();
		} else if (state == 2) {

			this.enemyWay = EnemyWay.RIGHT;
			left = true;
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
						// 왼쪽 상단
						if (stage.getPlayer().getX() == x && stage.getPlayer().getY() == y) {
							enemyRestart();
						}
						// 오른쪽상단
						if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
								&& stage.getPlayer().getY() == y) {
							enemyRestart();
						}
						// 왼쪽하단
						if (stage.getPlayer().getX() == x
								&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
							enemyRestart();
						}
						// 오른쪽 하단
						if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
								&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
							enemyRestart();
						}
						if (backgroundEnemyService.leftWall()) {
							
							break;
						}
						if (stage.getPlayer().getState() == 1) {
							left = false;
							beAttcked();
						}
					}
					if (backgroundEnemyService.leftWall()) {
						left = false;
						change();
					}
				}

			}).start();

		}
	}

	@Override
	public void right() {

		if (state != 2) {
			this.enemyWay = EnemyWay.RIGHT;
			right = true;
			setIcon(enemyR);

			new Thread(new Runnable() {
				@Override
				public void run() {
					while (right) {
						y -= SPEED;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (stage.getPlayer().getState() == 2) {
							right = false;
							beAttcked();
						}
						if (backgroundEnemyService.rightWall()) {
							
						}
					}
					if (backgroundEnemyService.rightWall()) {
						right = false;
						change();
					}
				}
			}).start();
		} else if (state == 2) {

			this.enemyWay = EnemyWay.RIGHT;
			right = true;
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (right) {
						x -= SPEED;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 왼쪽 상단
						if (stage.getPlayer().getX() == x && stage.getPlayer().getY() == y) {
							enemyRestart();
						}
						// 오른쪽상단
						if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
								&& stage.getPlayer().getY() == y) {
							enemyRestart();
						}
						// 왼쪽하단
						if (stage.getPlayer().getX() == x
								&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
							enemyRestart();
						}
						// 오른쪽 하단
						if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
								&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
							enemyRestart();
						}
						if (backgroundEnemyService.rightWall()) {
							
							break;
						}
						if (stage.getPlayer().getState() == 1) {
							right = false;
							beAttcked();
						}
					}
					if (backgroundEnemyService.rightWall()) {
						right = false;
						change();
					}
				}

			}).start();

		}
	}

	@Override
	public void up() {

		if (state != 2) {
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
						if (stage.getPlayer().getState() == 2) {
							up = false;
							beAttcked();
						}
						if (backgroundEnemyService.upWall()) {
							
							break;
						}
					}
					if (backgroundEnemyService.upWall()) {
						up = false;
						change();
					}
				}
			}).start();
		} else if (state == 2) {

			this.enemyWay = EnemyWay.UP;
			up = true;
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (up) {
						x -= SPEED;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 왼쪽 상단
						if (stage.getPlayer().getX() == x && stage.getPlayer().getY() == y) {
							enemyRestart();
						}
						// 오른쪽상단
						if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
								&& stage.getPlayer().getY() == y) {
							enemyRestart();
						}
						// 왼쪽하단
						if (stage.getPlayer().getX() == x
								&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
							enemyRestart();
						}
						// 오른쪽 하단
						if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
								&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
							enemyRestart();
						}
						if (backgroundEnemyService.upWall()) {
						
							break;
						}
						if (stage.getPlayer().getState() == 1) {
							up = false;
							beAttcked();
						}
					}
					if (backgroundEnemyService.upWall()) {
						up = false;
						change();
					}
				}

			}).start();

		}
	}

	@Override
	public void down() {
		if (state != 2) {
			this.enemyWay = EnemyWay.DOWN;
			down = true;
			setIcon(enemyD);
			if (backgroundEnemyService.downWall()) {
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
						if (stage.getPlayer().getState() == 2) {
							down = false;
							beAttcked();
						}
						if (backgroundEnemyService.downWall()) {
							
							break;
						}
					}
					if (backgroundEnemyService.downWall()) {
						down = false;
						change();
					}

				}
			}).start();

		} else if (state == 2) {
			this.enemyWay = EnemyWay.DOWN;
			down = true;
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (down) {
						x -= SPEED;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 왼쪽 상단
						if (stage.getPlayer().getX() == x && stage.getPlayer().getY() == y) {
							enemyRestart();
						}
						// 오른쪽상단
						if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
								&& stage.getPlayer().getY() == y) {
							enemyRestart();
						}
						// 왼쪽하단
						if (stage.getPlayer().getX() == x
								&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
							enemyRestart();
						}
						// 오른쪽 하단
						if (stage.getPlayer().getX() + beattackedBox == x + beattackedBox
								&& stage.getPlayer().getY() + beattackedBox == y + beattackedBox) {
							enemyRestart();
						}
						if (backgroundEnemyService.downWall()) {
							
							break;
						}
						if (stage.getPlayer().getState() == 1) {
							screamDown = false;
							beAttcked();
						}
					}
					if (backgroundEnemyService.downWall()) {
						down = false;
						change();
					}
				}

			}).start();
		}
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

	public void beAttcked() {

		System.out.println("작동3");

		if (stage.getPlayer().getState() == 2) {
			state = stage.getPlayer().getState();
			System.out.println("enemy 피격모드 작동");
			setIcon(enemyS);
			// screamchange();
			if (direction == 0) {
				left();
			} else if (direction == 1) {
				right();
			} else if (direction == 2) {
				up();
			} else {
				down();
			}
		} else if (stage.getPlayer().getState() != 2) {
			state = stage.getPlayer().getState();
			setIcon(enemyR);
			System.out.println("enemy 공격모드 작동");

			// change();
			if (direction == 0) {
				left();
			} else if (direction == 1) {
				right();
			} else if (direction == 2) {
				up();
			} else {
				down();
			}
		}

	}

}
