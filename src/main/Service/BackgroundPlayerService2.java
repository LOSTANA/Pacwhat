package main.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Components.Player;

public class BackgroundPlayerService2 implements Runnable {

	private BufferedImage image;
	private Player player;

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
			// 플레이어 캐릭터 인근의 벽 감지
			while (true) {

				Color leftColor = new Color(image.getRGB(player.getX(), player.getY() + 15));
				Color rightColor = new Color(image.getRGB(player.getX() + 40, player.getY() + 15));
				Color topColor = new Color(image.getRGB(player.getX() + 10, player.getY() -8));
				Color bottomColor = new Color(image.getRGB(player.getX() + 10, player.getY() + 35));

				// 벽 감지 -> 벽 충돌
				if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
					//System.out.println("왼쪽 벽에 충돌함");
					player.setLeftWallCrash(true);
					player.setLeft(false);
				} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
					//System.out.println("오른쪽 벽에 충돌함");
					player.setRightWallCrash(true);
					player.setRight(false);
				} else if (topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
					//System.out.println("위쪽 벽에 충돌함");
					player.setTopWallCrash(true);
					player.setUp(false);
				} else if (bottomColor.getRed() == 255 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 0) {
					//System.out.println("아래쪽 벽에 충돌함");
					player.setBottomWallCrash(true);
					player.setDown(false);
				} else {
					player.setLeftWallCrash(false);
					player.setRightWallCrash(false);
					player.setTopWallCrash(false);
					player.setBottomWallCrash(false);
				}
				// 벽에 부딪히지 않았다면(=감지되지 않았다면) 마음대로 움직일 수 있다.
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				


			}

		}		
	}
