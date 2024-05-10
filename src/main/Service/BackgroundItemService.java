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
	
	public BackgroundItemService(Item items) {
		this.item=items;
		
		try {
			// 코인 삭제를 위한 백그라운드 이미지 설정
			image = ImageIO.read(new File("img/background/BackgroundService.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		Color CoinColor = new Color(image.getRGB(item.getX(), item.getY()));
		if (CoinColor.getRed() == 237 && CoinColor.getGreen() == 27 && CoinColor.getBlue() == 36) {
			item.setCoin(null);
			System.out.println("작동");
			// 충돌하지 않았을 때
		} else {
			
		}
	}

}
