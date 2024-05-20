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

	@Override
	public void run() {
		this.player = stage.getPlayer();
		this.enemy1 = stage.getEnemy();
		this.enemy2 = stage.getEnemy2();
		this.enemy3 = stage.getEnemy3();
		// 플레이어 state가 1일 때(=살아 있을 때)
		// 플레이어-에너미 좌표를 확인 후, 중첩 시 공격 처리
		while (flag) {
			if (player.getState() != 2) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			}

			else if (player.getState() == 2) {
				for (int i = 0; i < 10; i++) {
					if (player.strong == true) {
						i = 0;
						player.strong = false;
					} else {

						// 에너미 1 감지
						if (Math.abs((player.getX() + 28 / 2) - (enemy1.getX() + 28 / 2)) < (28 / 2 + 28 / 2)
								&& Math.abs((player.getY() + 28 / 2) - (enemy1.getY() + 28 / 2)) < (28 / 2 + 28 / 2)) {
							// System.out.println(player.getEatedCount());
							// System.out.println(player.getScore());
							player.setEatedCount(player.getEatedCount() + 100);

							// 에너미 2 감지
						} else if (Math.abs((player.getX() + 28 / 2) - (enemy2.getX() + 28 / 2)) < (28 / 2 + 28 / 2)
								&& Math.abs((player.getY() + 28 / 2) - (enemy2.getY() + 28 / 2)) < (28 / 2 + 28 / 2)) {
							// System.out.println(player.getEatedCount());
							// System.out.println(player.getScore());
							player.setEatedCount(player.getEatedCount() + 100);

							// 에너미 3 감지
						} else if (Math.abs((player.getX() + 28 / 2) - (enemy3.getX() + 28 / 2)) < (28 / 2 + 28 / 2)
								&& Math.abs((player.getY() + 28 / 2) - (enemy3.getY() + 28 / 2)) < (28 / 2 + 28 / 2)) {
							// System.out.println(player.getEatedCount());
							// System.out.println(player.getScore());
							player.setEatedCount(player.getEatedCount() + 100);

						} else {
						}
					}
					try {
						System.out.println(i);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 무적상태 (6초 후) 원래 상태로 복귀
				player.setState(1);
			}

			else if (player.getState() == 9) {
				flag = false;
				break;
			}
		}
	}
}
