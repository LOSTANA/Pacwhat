package main.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Components.Enemy3;

public class BackgroundEnemyService3 {

	private BufferedImage image;
	private Enemy3 enemy3;

	public BackgroundEnemyService3(Enemy3 enemy3) {
		this.enemy3 = enemy3;

		try {
			image = ImageIO.read(new File("img/background/BackgroundService2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean leftWall() {
		// 255 0 0 <-- 빨간색 (왼쪽벽 확인)
		Color leftColor = new Color(image.getRGB(enemy3.getX() - 10, enemy3.getY() + 14));
		if ((leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) || (leftColor.getRed() == 0 && leftColor.getGreen() == 255 && leftColor.getBlue() == 0)) {
			return true;
		} else {
			return false;
		}
	}

	// 오른쪽벽
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(enemy3.getX() + 38, enemy3.getY() + 14));
		if ((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) || (rightColor.getRed() == 0 && rightColor.getGreen() == 255 && rightColor.getBlue() == 0)) {
			return true;
		} else {
			return false;
		}
	}

	// 위쪽벽
	public boolean upWall() {
		Color topColor = new Color(image.getRGB(enemy3.getX() + 14, enemy3.getY() - 10));
		if ((topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) || (topColor.getRed() == 0 && topColor.getGreen() == 255 && topColor.getBlue() == 0))  {
			return true;
		} else {
			return false;
		}
	}

	// 아래벽
	public boolean downWall() {
		Color downColor = new Color(image.getRGB(enemy3.getX() + 14, enemy3.getY() + 38));
		if ((downColor.getRed() == 255 && downColor.getGreen() == 0 && downColor.getBlue() == 0) || (downColor.getRed() == 0 && downColor.getGreen() == 255 && downColor.getBlue() == 0)) {
			return true;
		} else {
			return false;
		}

	}

}

