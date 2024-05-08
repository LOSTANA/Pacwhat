package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable{
	
	private int x;
	private int y;
	private ImageIcon player0, player1, player2, player3, player4; // playerT(playerTop) = 위 , playerB(playerBottom) = 아래

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
	
	
	// enum
	PlayerWay playerWay;
	
	public Player() {
		initData();
		setInitLayout();
	}
	
	private void initData() {
		player0 = new ImageIcon("img/pacman/pac0.png");
		player1 = new ImageIcon("img/pacman/pac1.png");
		player2 = new ImageIcon("img/pacman/pac2.png");
		player3 = new ImageIcon("img/pacman/pac3.png");
		player4 = new ImageIcon("img/pacman/pac4.png");
		
		// 초기위치값 추후 수정
		x = 100;
		y = 100;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		playerWay = PlayerWay.RIGHT;
		
	}
	private void setInitLayout() {
		setIcon(player0);
		setSize(28,28);
		setLocation(x,y);
	}
	
	public void attack() {
		
	}
	
	public void beAttacked() {
		
	}
	
	@Override
	public void left() {
		playerWay = PlayerWay.LEFT;
		left = true;
		setIcon(player0);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
			}
		});
	}

	@Override
	public void right() {}

	@Override
	public void up() {}

	@Override
	public void down() {}

}
