package main.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Components.Item;

public class BackgroundItemService implements Runnable {

	private BufferedImage image;
	private Item item;
	private Color leftcolor;
	private Color rightcolor;
	private Color topcolor;
	private Color bottomcolor;

	public BackgroundItemService(Item item) {
		this.item = item;
		try
		{
			// 코인 삭제를 위한 백그라운드 이미지 설정
			image = ImageIO.read(new File("img/background/BackgroundService2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while (true) {
			leftcolor = new Color(image.getRGB(item.getX() - 1, item.getY() + 14));
			rightcolor = new Color(image.getRGB(item.getX() + 30, item.getY() + 14));
			topcolor = new Color(image.getRGB(item.getX() + 14, item.getY() - 2));
			bottomcolor = new Color(image.getRGB(item.getX() + 14, item.getY() + 30));

			if ((leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0)
					|| (leftcolor.getRed() == 0 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 255)) {
				if (item.getState() == 0) {
					item.setIcon(null);
					item.setState(1);

				}
			} else if ((rightcolor.getRed() == 255 && rightcolor.getGreen() == 0 && rightcolor.getBlue() == 0)
					|| (rightcolor.getRed() == 0 && rightcolor.getGreen() == 0 && rightcolor.getBlue() == 255)) {
				if (item.getState() == 0) {
					item.setIcon(null);
					item.setState(1);

				}
			} else if ((topcolor.getRed() == 255 && topcolor.getGreen() == 0 && topcolor.getBlue() == 0)
					|| (topcolor.getRed() == 0 && topcolor.getGreen() == 0 && topcolor.getBlue() == 255)) {
				if (item.getState() == 0) {
					
					item.setIcon(null);
					item.setState(1);

				}
			} else if ((bottomcolor.getRed() == 255 && bottomcolor.getGreen() == 0 && bottomcolor.getBlue() == 0)
					|| (bottomcolor.getRed() == 0 && bottomcolor.getGreen() == 0 && bottomcolor.getBlue() == 255)) {
				if (item.getState() == 0) {
					item.setY(item.getY() - 5 );
					

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
