package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundEnemyService implements Runnable {

	private BufferedImage image;
	private Enemy enemy;

	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;

		try {
			image = ImageIO.read(new File("img/BackgroundService.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			Color leftColor =  new Color(image.getRGB(enemy.getX(), enemy.getY() + 25));
			Color rightColor =  new Color(image.getRGB(enemy.getX() + 50, enemy.getY() + 25));
			
		}

	}

}
