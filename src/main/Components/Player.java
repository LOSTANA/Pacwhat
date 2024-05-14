package main.Components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Maingame;
import main.Interface.Moveable;
import main.Service.BackgroundPlayerService;
import main.State.PlayerWay;

public class Player extends JLabel implements Moveable {

	Maingame stage;

	// 플레이어 가만히 있을 때 에너미 부딪혀도 목숨 줄어들게

	// 플레이어 살아있는상태 1, 죽은상태 0, 에너미를 먹을수 있는 상태 2 , 클리어 9
	private int state;
	private Item[] item;

	// 플레이어 목숨
	private int playerLife;

	// 점수
	private String score;

	private boolean clearFlag;

	private BackgroundPlayerService backgroundPlayerService;

	private int x;
	private int y;
	private int eatedCount;
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
	private final int SPEED = 4;

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

		// 초기위치값 임시로 설정 -- 추후 수정예정
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

		clearFlag = false; // 클리어시 true

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPlayrerLife() {
		return playerLife;
	}

	public void setPlayrerLife(int playrerLife) {
		this.playerLife = playrerLife;
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

	public boolean isClearFlag() {
		return clearFlag;
	}

	public void setClearFlag(boolean clearFlag) {
		this.clearFlag = clearFlag;
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
						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					for (int i = 5; i > 0; i--) {
						if (right == false) {
							break;
						}
						setIcon(imageIconR[i - 1]);
						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
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
						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					for (int i = 5; i > 0; i--) {
						setIcon(imageIconL[i - 1]);
						if (left == false) {
							break;
						}
						try {
							Thread.sleep(80);
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
					if (state == 0) {
						break;
					}
					x = x - SPEED;
					setLocation(x, y);

					if (x <= 70) {
						bridgeLeft();
					}
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
					if (state == 0) {
						break;
					}
					x = x + SPEED;
					setLocation(x, y);
					if (x >= 680) {
						bridgeRight();
					}
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
	public void up() {
		playerWay = PlayerWay.UP;
		up = true;

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (up) {
					if (state == 0) {
						break;
					}
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
	public void down() {
		playerWay = PlayerWay.DOWN;
		down = true;

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (down) {
					if (state == 0) {
						break;
					}
					y = y + SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					eated();
				}

			} // end of while

		}).start();
	}

	// 플레이어 완전히 죽었을때 ( life -> 0)
	// state 1 -- > 0
	// 플레이어 완전히 죽었을때 ( life -> 0)
    // state 1 -- > 0
    public void completelyDie() {

        state = 0;
        stage.getPlayer().setIcon(null);
        stage.healthScreen[1].setIcon(null);
        stage.remove(stage.getPlayer());
        stage.healthScreen[0].setText("----- YOU DIE -----");
    }

	// 공격가능한 상태  
	public void attackable() {
		state = 2;

	}

	public void lostLifeMotion() {
        if(playerLife>=0) {
            for (int i = 0; i <= 3; i++) {
                setIcon(imageIconR[0]);
                stage.healthScreen[playerLife+1].setIcon(imageIconR[4]);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setIcon(null);
                stage.healthScreen[playerLife+1].setIcon(null);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } 

        }

        setIcon(imageIconR[0]);

    }

	public void beAttackedAlways() {
		isBeAttacked2();
		isBeAttacked1();
		isBeAttacked3();
	}

	// 플레이어 에너미1랑 부딪힐 경우
	public void isBeAttacked1() {
		int absXResult = Math.abs(x - stage.getEnemy().getX());
		int absYResult = Math.abs(y - stage.getEnemy().getY());
		if (absXResult < 35 && absYResult < 35) {
			this.state = 0;

			playerLife--;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (stage.getPlayer().getState() == 0) {
				if (playerLife != 0) {
					lostLifeMotion();
				} else {
					completelyDie();
				}
				try {
					Thread.sleep(1150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	// 플레이어 에너미2랑 부딪힐 경우
	public void isBeAttacked2() {
		int absXResult = Math.abs(x - stage.getEnemy2().getX());
		int absYResult = Math.abs(y - stage.getEnemy2().getY());
		if (absXResult < 35 && absYResult < 35) {
			this.state = 0;

			playerLife--;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (stage.getPlayer().getState() == 0) {
				if (playerLife != 0) {
					lostLifeMotion();
				} else {
					completelyDie();
				}
				try {
					Thread.sleep(1150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	// 플레이어 에너미3랑 부딪힐 경우
	public void isBeAttacked3() {
		int absXResult = Math.abs(x - stage.getEnemy3().getX());
		int absYResult = Math.abs(y - stage.getEnemy3().getY());
		if (absXResult < 35 && absYResult < 35) {
			this.state = 0;

			playerLife--;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (stage.getPlayer().getState() == 0) {
				if (playerLife != 0) {
					lostLifeMotion();
				} else {
					completelyDie();
				}
				try {
					Thread.sleep(1150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
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
	// 추후 수정
	public void clearStage() {

		clearFlag = true;
		System.out.println("클리어 스테이지");
		while (clearFlag) {
			while (true) {

				left = false;
				right = false;
				up = false;
				down = false;

				state = 9;
				eatedCount = 0;
				stage.scoreScreen.setText("--- Clear!!! ---");
			}
		}
	}

	// 먹기 구현
	public void eated() {

		for (int i = 0; i < 239; i++) {
			int absXResult = Math.abs(x - stage.getItem()[i].getX());
			int absYResult = Math.abs(y - stage.getItem()[i].getY());
			if (absXResult < 23 && absYResult < 23
					&& (stage.getItem()[i].getState() == 0 || stage.getItem()[i].getState() == 2)) {
				if (stage.getItem()[i].getState() == 0) {
					stage.getItem()[i].setIcon(null);
					stage.getItem()[i].setState(1);
					eatedCount += 10;
				} else if (stage.getItem()[i].getState() == 2) {
					stage.getItem()[i].setIcon(null);
					stage.getItem()[i].setState(1);
					eatedCount += 50;
				}
			}
			score = Integer.toString(eatedCount);
			stage.scoreScreen.setText("점수 : " + eatedCount);
			if (eatedCount == 1470) {
				clearStage();
			}
		}

	}

} // end of class
