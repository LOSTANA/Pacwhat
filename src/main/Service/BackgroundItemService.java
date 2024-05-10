package main.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Components.Item;
import main.Components.Player;

public class BackgroundItemService implements Runnable {

	private BufferedImage image;
	private Item item;
	private Color color;

	public BackgroundItemService(Item item) {
		this.item = item;
		try

		{
			// 코인 삭제를 위한 백그라운드 이미지 설정
			image = ImageIO.read(new File("img/background/BackgroundService.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			color = new Color(image.getRGB(item.getX(), item.getY()));
			if (color.getRed() == 237 && color.getGreen() == 27 && color.getBlue() == 36) {
				if (item.getState() == 0) {
					item.setIcon(null);
					item.setState(1);
					System.out.println(item.getX() + " , " + item.getY() + "작동");
				}
				// 충돌하지 않았을 때
			} else {

			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

}
