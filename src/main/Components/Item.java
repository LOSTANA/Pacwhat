package main.Components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Maingame;
import main.Service.BackgroundItemService;
import main.Service.BackgroundPlayerService;

public class Item extends JLabel {

	private Maingame stage;
	private BackgroundItemService backgroundItemService;
	private Player player;
	
	private int x;
	private int y;
	
	private int state;
	
	private ImageIcon coin;
	
	public Item(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();
		eated();
		new Thread(new BackgroundItemService(this)).start();
	}

	public Maingame getStage() {
		return stage;
	}

	public void setStage(Maingame stage) {
		this.stage = stage;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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

	public ImageIcon getCoin() {
		return coin;
	}

	public void setCoin(ImageIcon coin) {
		this.coin = coin;
	}

	private void initData() {
		coin = new ImageIcon("img/item/coin.png");
		state = 0;
		backgroundItemService = new BackgroundItemService(this);
	}
	
	private void setInitLayout() {
		
		x = 30;
		y = 30;
		
		setIcon(coin);
		setSize(28,28);
		setLocation(x,y);
		state = 0;
	}
	
	private void eated() {
		
		this.player = stage.getPlayer();
		
		if(player.getX() == x && player.getY() == y) {
			setIcon(null);
			setLocation(x,y);
		}
	}
	
	
		
	

	
	

}
