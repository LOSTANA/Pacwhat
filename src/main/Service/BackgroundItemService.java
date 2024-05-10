package main.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Components.Item;
import main.Components.Player;

public class BackgroundItemService {

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
		for (int i = 0; i < 324; i++) {
			color = new Color(image.getRGB(item.getX(), item.getY()));
			if (color.getRed() == 237 && color.getGreen() == 27 && color.getBlue() == 36) {
				item.setIcon(null);
				System.out.println("작동");
				// 충돌하지 않았을 때
			} else {

			}
		}
	}

}
