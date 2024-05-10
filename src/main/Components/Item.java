package main.Components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Maingame;

public class Item extends JLabel {

    Maingame stage;
    private Player player;
    
    private ImageIcon coin;
    private int x;
    private int y;
    
    private int state; // 0.(기본상태), 1.먹힌상태
    
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
	

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Item(Maingame stage){
    	this.stage = stage;
    	this.player = stage.getPlayer();
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
    
    public void eated() {
    	int absX = Math.abs(x - stage.getPlayer().getX());
    	int absY = Math.abs(y - stage.getPlayer().getY());
    	if(absX < 10 && absY < 50) {
    		
    	}
    	
    	state = 1;
    	
    	
    }


    
}