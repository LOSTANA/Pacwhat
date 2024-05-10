package main.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Components.Enemy2;
import main.Components.Enemy3;

public class BackgroundEnemyService3 {

	private BufferedImage image;
	private Enemy3 enemy3;

	public BackgroundEnemyService3(Enemy3 enemy3) {
		this.enemy3 = enemy3;

		try {
			image = ImageIO.read(new File("img/background/BackgroundService.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean leftWall() {
		// 255 0 0 <-- 빨간색 (왼쪽벽 확인)
		Color leftColor = new Color(image.getRGB(enemy3.getX() - 10, enemy3.getY() + 25));
		if (leftColor.getRed() == 237 && leftColor.getGreen() == 27 && leftColor.getBlue() == 36) {
			return true;
		}
		return false;
	}

	// 오른쪽벽
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(enemy3.getX() + 60, enemy3.getY() + 25));
		if (rightColor.getRed() == 237 && rightColor.getGreen() == 27 && rightColor.getBlue() == 36) {
			return true;
		}
		return false;
	}

	// 위쪽벽
	public boolean upWall() {
		Color topColor = new Color(image.getRGB(enemy3.getX() + 25, enemy3.getY() - 10));
		if (topColor.getRed() == 237 && topColor.getGreen() == 27 && topColor.getBlue() == 36) {
			return true;
		}
		return false;
	}

	// 아래벽
	public boolean downWall() {
		Color downColor = new Color(image.getRGB(enemy3.getX() + 25, enemy3.getY() + 60));
		if (downColor.getRed() == 237 && downColor.getGreen() == 27 && downColor.getBlue() == 36) {
			return true;
		}
		return false;

	}

}
