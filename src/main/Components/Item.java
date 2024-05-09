package main.Components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Maingame;

public class Item extends JLabel {

    Maingame stage;
    
    private ImageIcon coin;
    private int x;
    private int y;
    
    
    public Maingame getStage() {
		return stage;
	}

	public void setStage(Maingame stage) {
		this.stage = stage;
	}

	public ImageIcon getCoin() {
		return coin;
	}

	public void setCoin(ImageIcon coin) {
		this.coin = coin;
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

	public Item(Maingame stage){
    	this.stage = stage;
    	initData();
    	setInitLayout();
    	
    }
    
    private void initData() {
    	
    	coin = new ImageIcon("img/item/coin.png");

    	
    }
    
    private void setInitLayout() {
    	setIcon(coin);
    	setSize(28,28);
    	setLocation(x, y);
    }


    
}