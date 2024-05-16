package main.Service;

import java.awt.Color;
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
	private Player player;
	private Maingame stage;
	private Enemy1 enemy1;
	private Enemy2 enemy2;
	private Enemy3 enemy3;

	// 생성자 의존 설계
	public BackgroundPlayerService2(Player player, Enemy1 enemy1, Enemy2 enemy2, Enemy3 enemy3) {
		this.player = player;
		this.stage = player.getStage();
		this.enemy1 = enemy1;
		this.enemy2 = enemy2;
		this.enemy3 = enemy3;
		try {
			// 플레이어 캐릭터 기본 이미지 설정
			image = ImageIO.read(new File("img/background/BackgroundService2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {

		// 플레이어 state가 1일 때(=살아 있을 때)
		// 플레이어-에너미 좌표를 확인 후, 중첩 시 공격 처리
		while (player.getState() != 2) {
			// 에너미1-좌표 감지
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
			} else {

			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}