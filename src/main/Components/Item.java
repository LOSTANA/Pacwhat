package main.Components;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Maingame;

public class Item extends JLabel {

	Maingame stage;
	private Player player;

	private ImageIcon coin1 = new ImageIcon("img/item/coin.png");

	List<Item> coinList = new ArrayList<>();
	private int x;
	private int y;
	private int[][] colorArr;
	private int[] sizeArr;

	private int state; // 0.(기본상태), 1.먹힌상태

	public Maingame getStage() {
		return stage;
	}

	public void setStage(Maingame stage) {
		this.stage = stage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Item(Maingame stage) {
		this.stage = stage;
		this.player = stage.getPlayer();
		initData();
		setInitLayout();

	}

	private void initData() {

		setFocusable(true);
		
		try {
			sizeArr = getSize("img/background/BackgroundService.jpg");
			colorArr = getPic("img/background/BackgroundService.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int maxX = sizeArr[0];
		int maxY = sizeArr[1];
		
		for(int i = 0;i < maxX;i++) {
			for(int j = 0;j<maxY;j++) {
				if(colorArr[i][j] == 0) {
					coinList.add(new (coin1.getImage(), i * 40,j * 40,28,28,255));
				}
			}
		}
		
		for (int i = 0;i<coinList.size();i++) {
			coinList.get(i).setX(coinList.get(i).getX() -4);
		}
		
		List<Integer> countList = 

	}

	private void setInitLayout() {

	}

	static int[] getSize(String src) throws Exception {
		File imgf = new File("img/item/coin.png");
		BufferedImage img = ImageIO.read(imgf);
		int width = img.getWidth();
		int height = img.getHeight();
		int[] temPos = { width, height };
		return temPos;
	}

	static int[][] getPic(String src) throws Exception { // 아래 코드는 어디다가 갖다두고 쓰자.
		File imgf = new File("img/item/coin.png");
		BufferedImage img = ImageIO.read(imgf);
		int width = img.getWidth();
		int height = img.getHeight();
		int[] pixels = new int[width * height];
		PixelGrabber grab = new PixelGrabber(img, 0, 0, width, height, pixels, 0, width);
		grab.grabPixels();

		int[][] picture = new int[width][height];
		for (int i = 0; i < pixels.length; i++)
			picture[i % width][i / width] = pixels[i] + 16777216;
		return picture;
	}

}