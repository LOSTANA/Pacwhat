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
		while(true) {
			// enemy1과의 충돌 측정
			int distanceX1=Math.abs(player.getX()-enemy1.getX());
			int distanceY1=Math.abs(player.getY()-enemy1.getY());
			
			// enemy2과의 충돌 측정
			int distanceX2=Math.abs(player.getX()-enemy2.getX());
			int distanceY2=Math.abs(player.getY()-enemy2.getY());
			
			// enemy3과의 충돌 측정
			int distanceX3=Math.abs(player.getX()-enemy3.getX());
			int distanceY3=Math.abs(player.getY()-enemy3.getY());
			
			// 만약 플레이어가 살아있다면
			if(player.getState()==1) {
				if(distanceX1<20 && distanceY1<20) {
					player.isBeAttacked1();
				} else if(distanceX2<20 && distanceY2<20) {
					player.isBeAttacked2();
				} else if(distanceX3<20 && distanceY3<20) {
					player.isBeAttacked3();
				} else {
				}
			}
		}

	}

}
