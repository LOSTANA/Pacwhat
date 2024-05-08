package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel implements Moveable {

	Maingame stage;

	// 적군의 좌표값 위치 상태
	private int x;
	private int y;
	private ImageIcon enemyR, enemyL, enemyD, enemyU;
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

	// 적군 속도 상태
	private final int SPEED = 3; // 수정

	public Enemy(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();
	}

	private void initData() {

		enemyR = new ImageIcon("img/ghost1/cyanR1.png");
		enemyL = new ImageIcon("img/ghost1/cyanR2.png");
		enemyD = new ImageIcon("img/ghost1/cyanD1.png");
		enemyU = new ImageIcon("img/ghost1/cyanU1.png");
		backgroundEnemyService = new BackgroundEnemyService(this);

		// 에너미가 가만히 멈춤 상태
		left = false;
		right = false;
		up = false;
		down = false;

		enemyWay = EnemyWay.RIGHT;

		setIcon(enemyD);
		setSize(50, 50); // 수정 해야됨
		setLocation(x, y);

		// 처음 실행 시 초기 값 셋팅 (수정)
		x = 620;
		y = 230;

	}

	private void setInitLayout() {

	}

	@Override
	public void left() {
		this.enemyWay = EnemyWay.LEFT;
		left = true;
		setIcon(enemyL);
		for (int i = 0; i < 1000; i++) {
			x--;
			setLocation(x, y);

			if (backgroundEnemyService.leftWall()) {
				break;
			}
			up();
			if (up == backgroundEnemyService.upWall()) {
				break;
			}
			right();
			if (right == backgroundEnemyService.rightWall()) {
				break;
			}

			down();
			if (down == backgroundEnemyService.downWall()) {
				break;
			}
		}
	}

	@Override
	public void right() {
		this.enemyWay = EnemyWay.RIGHT;
		right = true;
		setIcon(enemyR);
		for (int i = 0; i < 800; i++) {
			x++;
			setLocation(x, y);
			if (backgroundEnemyService.rightWall()) {
				break;
			}
			down();
			if (down == backgroundEnemyService.downWall()) {
				break;
			}
			left();
			if (left == backgroundEnemyService.leftWall()) {
				break;
			}
			up();
			if (up == backgroundEnemyService.upWall()) {
				break;
			}
		}

	}

	@Override
	public void up() {
		this.enemyWay = EnemyWay.UP;
		up = true;
		setIcon(enemyU);
		for (int i = 0; i < 800; i++) {
			if (backgroundEnemyService.upWall()) {
				break;
			}
			right();
			if (right == backgroundEnemyService.rightWall()) {
				break;
			}
			down();
			if (down == backgroundEnemyService.downWall()) {
				break;
			}
			up();
			if (up == backgroundEnemyService.upWall()) {
				break;
			}
		}

	}

	@Override
	public void down() {
		this.enemyWay = EnemyWay.DOWN;
		down = true;
		setIcon(enemyD);
		for (int i = 0; i < 800; i++) {
			if (backgroundEnemyService.downWall()) {
				break;
			}
			left();
			if (left == backgroundEnemyService.leftWall()) {
				break;
			}
			up();
			if (up == backgroundEnemyService.upWall()) {
				break;
			}
			right();
			if (right == backgroundEnemyService.rightWall()) {
				break;
			}
		}

	}

	// get,set
	public Maingame getmContext() {
		return stage;
	}

	public void setmContext(Maingame stage) {
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
