package main.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Components.Enemy1;

public class BackgroundEnemyService {

	private BufferedImage image;
	private Enemy1 enemy;

	public BackgroundEnemyService(Enemy1 enemy) {
		this.enemy = enemy;

		try {
			image = ImageIO.read(new File("img/background/BackgroundService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean leftWall() {
		// 255 0 0 <-- 빨간색 (왼쪽벽 확인)
		Color leftColor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY() + 25));
		if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			return true;
		}
		return false;
	}

	// 오른쪽벽
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(enemy.getX() + 60, enemy.getY() + 25));
		if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			return true;
		}
		return false;
	}

	// 위쪽벽
	public boolean upWall() {
		Color topColor = new Color(image.getRGB(enemy.getX() + 25, enemy.getY() - 10));
		if (topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
			return true;
		}
		return false;
	}

	// 아래벽
	public boolean downWall() {
		Color downColor = new Color(image.getRGB(enemy.getX() + 25, enemy.getY() + 60));
		if (downColor.getRed() == 255 && downColor.getGreen() == 0 && downColor.getBlue() == 0) {
			return true;
		}
		return false;

	}

}
