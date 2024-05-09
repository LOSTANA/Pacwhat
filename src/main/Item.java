package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Item extends JLabel{
	
	private int x;
	private int y;

	private Maingame stage;
	
	private ImageIcon coin;
	
	public Item(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();
		if(x == 750) {
			y = y + 10;
			x = 0;
		}else {
			x = x + 10;
		}
		
	}
	
	private void initData() {
		coin = new ImageIcon("img/item/coin.png");
	}
	
	private void setInitLayout() {
		setIcon(coin);
		setSize(28,28);
		setLocation(x,y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ImageIcon getCoin() {
		return coin;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setCoin(ImageIcon coin) {
		this.coin = coin;
	}

}
