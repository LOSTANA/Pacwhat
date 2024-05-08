package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	// 생성자 의존 설계
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			// 플레이어 캐릭터 기본 이미지 설정
			image = ImageIO.read(new File("img/background/BackgroundService.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
<<<<<<< HEAD
		// 플레이어 캐릭터 인근의 벽 감지
		Color leftColor = new Color(image.getRGB(player.getX()+20, player.getY()+15));
		Color rightColor = new Color(image.getRGB(player.getX()-20, player.getY()+15));
		Color topColor = new Color(image.getRGB(player.getX()+10, player.getY()-35));
		Color bottomColor = new Color(image.getRGB(player.getX()+10, player.getY()+35));
		
		// 벽 감지 -> 벽 충돌
		if(leftColor.getRed() ==255 && leftColor.getGreen()==0 && leftColor.getBlue() == 0) {
			System.out.println("왼쪽 벽에 충돌함");
			player.setLeftWallCrash(true);
			player.setLeft(false);
		} else if(rightColor.getRed() ==255 && rightColor.getGreen()==0 && rightColor.getBlue() == 0) {
			System.out.println("오른쪽 벽에 충돌함");
			player.setRightWallCrash(true);
			player.setRight(false);
		} else if(topColor.getRed() ==255 && rightColor.getGreen()==0 && rightColor.getBlue() == 0) {
			System.out.println("위쪽 벽에 충돌함");
			player.setTopWallCrash(true);
			player.setUp(false);
		} else if(bottomColor.getRed() ==255 && rightColor.getGreen()==0 && rightColor.getBlue() == 0) {
			System.out.println("아래쪽 벽에 충돌함");
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
=======
		while (true) {
>>>>>>> bbb9af715f63d33013378d4c89033502e48ece6b

			// 플레이어 캐릭터 인근의 벽 감지
			while (true) {

				Color leftColor = new Color(image.getRGB(player.getX() +10, player.getY() + 15));
				Color rightColor = new Color(image.getRGB(player.getX() + 28, player.getY() + 15));
				Color topColor = new Color(image.getRGB(player.getX() + 10, player.getY() -10));
				Color bottomColor = new Color(image.getRGB(player.getX() + 10, player.getY() + 35));

				// 벽 감지 -> 벽 충돌
				if (leftColor.getRed() == 237 && leftColor.getGreen() == 27 && leftColor.getBlue() == 36) {
					System.out.println("왼쪽 벽에 충돌함");
					player.setLeftWallCrash(true);
					player.setLeft(false);
				} else if (rightColor.getRed() == 237 && rightColor.getGreen() == 27 && rightColor.getBlue() == 36) {
					System.out.println("오른쪽 벽에 충돌함");
					player.setRightWallCrash(true);
					player.setRight(false);
				} else if (topColor.getRed() == 237 && topColor.getGreen() == 27 && topColor.getBlue() == 36) {
					System.out.println("위쪽 벽에 충돌함");
					player.setTopWallCrash(true);
					player.setUp(false);
				} else if (bottomColor.getRed() == 237 && bottomColor.getGreen() == 27 && bottomColor.getBlue() == 36) {
					System.out.println("아래쪽 벽에 충돌함");
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
}
