package main.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Maingame;
import main.Components.Enemy1;
import main.Components.Enemy2;
import main.Components.Enemy3;
import main.Components.Player;

public class BackgroundPlayerService2 implements Runnable {

	private BufferedImage image;
	private Maingame stage;
	private Player player;
	private Enemy1 enemy1;
	private Enemy2 enemy2;
	private Enemy3 enemy3;
	private boolean flag = true;
	private boolean strong = false;

	private int width = 784;
	public int a;

	// 생성자 의존 설계
	public BackgroundPlayerService2(Maingame stage) {
		this.stage = stage;
		
		try {
			// 플레이어 캐릭터 기본 이미지 설정
			image = ImageIO.read(new File("img/background/BackgroundService2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isStrong() {
		return strong;
	}

	public void setStrong(boolean strong) {
		this.strong = strong;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		this.player = stage.getPlayer();

		this.enemy1 = stage.getEnemy();
		this.enemy2 = stage.getEnemy2();
		this.enemy3 = stage.getEnemy3();
		// 플레이어 state가 1일 때(=살아 있을 때)
		// 플레이어-에너미 좌표를 확인 후, 중첩 시 공격 처리
		System.out.println("백그2 시작");
		while (flag) {
			System.out.println("1");
			if (player.getState() == 1) {
				if (Math.abs((player.getX() + 28 / 2) - (enemy1.getX() + 28 / 2)) < (28 / 2 + 28 / 2)
						&& Math.abs((player.getY() + 28 / 2) - (enemy1.getY() + 28 / 2)) < (28 / 2 + 28 / 2)) {
					player.isBeAttacked1();
					// 에너미2-좌표 감지
				} else if (Math.abs((player.getX() + 28 / 2) - (enemy2.getX() + 28 / 2)) < (28 / 2 + 28 / 2)
						&& Math.abs((player.getY() + 28 / 2) - (enemy2.getY() + 28 / 2)) < (28 / 2 + 28 / 2)) {
					player.isBeAttacked2();
					// 에너미3-좌표 감지
				} else if (Math.abs((player.getX() + 28 / 2) - (enemy3.getX() + 28 / 2)) < (28 / 2 + 28 / 2)
						&& Math.abs((player.getY() + 28 / 2) - (enemy3.getY() + 28 / 2)) < (28 / 2 + 28 / 2)) {
					player.isBeAttacked3();
				}
			}

			else if (player.getState() == 2) {

				System.out.println("if 통과");

				for (int i = 0; i < 10; i++) {
					if (player.strong == true) {
						i = 0;
						player.strong=false; // 중복 방지
					}
					System.out.println("while 시작");
					// 에너미 1 감지
					if (Math.abs((player.getX() + 28 / 2) - (enemy1.getX() + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (enemy1.getY() + 28 / 2)) < (28 / 2 + 28 / 2)) {
						System.out.println("적 공격 시작1");
						// System.out.println(player.getEatedCount());
						// System.out.println(player.getScore());
						player.setEatedCount(player.getEatedCount() + 100);
						System.out.println(player.getEatedCount());

						// 에너미 2 감지
					} else if (Math.abs((player.getX() + 28 / 2) - (enemy2.getX() + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (enemy2.getY() + 28 / 2)) < (28 / 2 + 28 / 2)) {
						System.out.println("적 공격 시작2");
						// System.out.println(player.getEatedCount());
						// System.out.println(player.getScore());
						player.setEatedCount(player.getEatedCount() + 100);
						System.out.println(player.getEatedCount());
						

						// 에너미 3 감지
					} else if (Math.abs((player.getX() + 28 / 2) - (enemy3.getX() + 28 / 2)) < (28 / 2 + 28 / 2)
							&& Math.abs((player.getY() + 28 / 2) - (enemy3.getY() + 28 / 2)) < (28 / 2 + 28 / 2)) {
						System.out.println("적 공격 시작3");
						// System.out.println(player.getEatedCount());
						// System.out.println(player.getScore());
						player.setEatedCount(player.getEatedCount() + 100);
						System.out.println(player.getEatedCount());

					} else {
						System.out.println("if 통과 못함");
					}
					// System.out.println(i+"번째");

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(i + "초 경과");
				}
				// 무적상태 (6초 후) 원래 상태로 복귀
				System.out.println("set state==1");
				player.setState(1);
				player.strong = false;
			}

			else if (player.getState() == 9) {
				flag = false;
				break;
			}

		}
	}
}