package main.Components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Frame.Maingame;
import main.Service.BackgroundItemService;

public class Item extends JLabel {

	private Maingame stage;
	private Player player;

	private int x;
	private int y;

	private int state;
	private int bonus;

	private ImageIcon coin;
	private ImageIcon item1;
	private ImageIcon item2;
	private ImageIcon item3;
	private ImageIcon item4;

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

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	

	private void initData() {
		coin = new ImageIcon("img/item/coin.png");
		item1 = new ImageIcon("img/item/cherry.png");
		item2 = new ImageIcon("img/item/melon.png");
		item3 = new ImageIcon("img/item/orange.png");
		item4 = new ImageIcon("img/item/strawberry.png");
		state = 0;
	}

	private void setInitLayout() {

		x = 30;
		y = 110;

		setIcon(coin);
		setSize(28, 28);
		setLocation(x, y);
		state = 0;
	}

	private void eated() {

		this.player = stage.getPlayer();

		if (player.getX() == x && player.getY() == y) {
			setIcon(null);
			setLocation(x, y);
			state = 1;
		}
	}

	public void transitem(int bonus) {
		if (bonus == 0) {
			setIcon(item1);
		} else if (bonus == 1) {
			setIcon(item2);

		} else if (bonus == 2) {
			setIcon(item3);
		} else if (bonus == 3) {
			setIcon(item4);
		}
		state = 2;
	}

}
