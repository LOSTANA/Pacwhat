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
	private Enemy1 enemy1;
	private Enemy2 enemy2;
	private Enemy3 enemy3;
	private Maingame stage;

	// 생성자 의존 설계
	public BackgroundPlayerService2(Player player) {
		this.player = player;
		this.enemy1=stage.getEnemy();
		this.enemy2=stage.getEnemy2();
		this.enemy3=stage.getEnemy3();
		try {
			// 플레이어 캐릭터 기본 이미지 설정
			image = ImageIO.read(new File("img/background/BackgroundService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while(true) {
			// Enemy1의 위치
			int absXResult = Math.abs(player.getX() - enemy1.getX());
			int absYResult = Math.abs(player.getY() - enemy1.getY());
			
			// Enemy2의 위치
			int absXResult2 = Math.abs(player.getX() - enemy2.getX());
			int absYResult2 = Math.abs(player.getY() - enemy2.getY());
			
			// Enemy3의 위치
			int absXResult3 = Math.abs(player.getX()- enemy3.getX());
			int absYResult3 = Math.abs(player.getY()- enemy3.getY());
			
			// 에너미 1과 마주쳤을 때
			if (absXResult < 23 && absYResult < 23 ) {
				player.setState(0);
				player.setPlayerLife(player.getPlayerLife()-1);
				if (stage.getPlayer().getState() == 0) {
					if (player.getPlayerLife()== 0) {
						player.beAttacked();
					} else {
						player.lostLifeMotion();
						try {
							Thread.sleep(1150);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			// 에너미 2와 마주쳤을 때
			else if (absXResult2 < 23 && absYResult2 < 23 ) {
				player.setState(0);
				player.setPlayerLife(player.getPlayerLife()-1);
				if (stage.getPlayer().getState() == 0) {
					if (player.getPlayerLife()== 0) {
						player.beAttacked();
					} else {
						player.lostLifeMotion();
						try {
							Thread.sleep(1150);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}	// 에너미 3와 마주쳤을 때
			else if (absXResult3 < 23 && absYResult3 < 23 ) {
				player.setState(0);
				player.setPlayerLife(player.getPlayerLife()-1);
				if (stage.getPlayer().getState() == 0) {
					if (player.getPlayerLife()== 0) {
						player.beAttacked();
					} else {
						player.lostLifeMotion();
						try {
							Thread.sleep(1150);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				continue;
			}
			
		}
}
}
