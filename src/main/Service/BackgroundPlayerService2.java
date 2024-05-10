package main.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Maingame;
import main.Components.Player;

public class BackgroundPlayerService2 implements Runnable {

	private BufferedImage image;
	private Player player;
	private Maingame stage;

	// 생성자 의존 설계
	public BackgroundPlayerService2(Player player) {
		this.player = player;
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
			int absXResult = Math.abs(player.getX() - stage.getEnemy().getX());
			int absYResult = Math.abs(player.getY() - stage.getEnemy().getY());
			
			int absXResult2 = Math.abs(player.getX() - stage.getEnemy2().getX());
			int absYResult2 = Math.abs(player.getY() - stage.getEnemy2().getY());
			
			int absXResult3 = Math.abs(player.getX()- stage.getEnemy3().getX());
			int absYResult3 = Math.abs(player.getY()- stage.getEnemy3().getY());
			
			// 에너미 1과 마주쳤을 때
			if (absXResult < 23 && absYResult < 23 ) {
				player.setState(0);
				player.setPlayrerLife(player.getPlayrerLife()-1);
				if (stage.getPlayer().getState() == 0) {
					if (player.getPlayrerLife()== 0) {
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
				player.setPlayrerLife(player.getPlayrerLife()-1);
				if (stage.getPlayer().getState() == 0) {
					if (player.getPlayrerLife()== 0) {
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
				player.setPlayrerLife(player.getPlayrerLife()-1);
				if (stage.getPlayer().getState() == 0) {
					if (player.getPlayrerLife()== 0) {
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
