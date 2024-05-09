package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundEnemyService2 {

	private BufferedImage image;
	private Enemy2 enemy2;

	public BackgroundEnemyService2(Enemy2 enemy2) {
		this.enemy2 = enemy2;

		try {
			image = ImageIO.read(new File("img/background/BackgroundService.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean leftWall() {
		// 255 0 0 <-- 빨간색 (왼쪽벽 확인)
		Color leftColor = new Color(image.getRGB(enemy2.getX() - 10, enemy2.getY() + 25));
		if (leftColor.getRed() == 237 && leftColor.getGreen() == 27 && leftColor.getBlue() == 36) {
			return true;
		}
		return false;
	}

	// 오른쪽벽
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(enemy2.getX() + 60, enemy2.getY() + 25));
		if (rightColor.getRed() == 237 && rightColor.getGreen() == 27 && rightColor.getBlue() == 36) {
			return true;
		}
		return false;
	}

	// 위쪽벽
	public boolean upWall() {
		Color topColor = new Color(image.getRGB(enemy2.getX() + 25, enemy2.getY() - 10));
		if (topColor.getRed() == 237 && topColor.getGreen() == 27 && topColor.getBlue() == 36) {
			return true;
		}
		return false;
	}

	// 아래벽
	public boolean downWall() {
		Color downColor = new Color(image.getRGB(enemy2.getX() + 25, enemy2.getY() + 60));
		if (downColor.getRed() == 237 && downColor.getGreen() == 27 && downColor.getBlue() == 36) {
			return true;
		}
		return false;

	}

}
