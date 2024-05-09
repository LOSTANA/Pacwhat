package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Item extends JLabel {

    Maingame stage;
    private int x;
    private int y;
    private ImageIcon coin;
    
    public Item(Maingame stage){
    	this.stage = stage;
    	initData();
    	setInitLayout();
    	
    }
    
    private void initData() {
    	
    	coin = new ImageIcon("img/item/coin.png");
    	x = 20;
    	y = 20;
    	
    }
    
    private void setInitLayout() {
    	setIcon(coin);
    	setSize(28,28);
    	setLocation(x,y);
    	
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
    
    
}