package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Components.Enemy1;

public class PauseGame extends JFrame {

	private DrawLogo drawLogo;
	private Maingame stage;
	private Enemy1 enemy1;

	public PauseGame(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();
		addEventListener();

	}

	private void initData() {

		setSize(750, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 122));

		drawLogo = new DrawLogo();

		setLayout(new BorderLayout());
		drawLogo.setBackground(new Color(255, 255, 255, 0));
	}

	private void setInitLayout() {

		add(drawLogo, BorderLayout.CENTER);

		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void addEventListener() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (stage.getEnemy().getX() <= 415 && stage.getEnemy().getX() >= 291 && stage.getEnemy().getY() >= 412
						&& stage.getEnemy().getY() <= 468) {
					stage.getEnemy().enemyStart();
				} else {

					if (stage.getEnemy().getDirection() == 0) {
						stage.getEnemy().left();
					} else if (stage.getEnemy().getDirection() == 1) {
						stage.getEnemy().right();
					} else if (stage.getEnemy().getDirection() == 2) {
						stage.getEnemy().up();
					} else {
						stage.getEnemy().down();
					}
				}
				if (stage.getEnemy2().getX() <= 415 && stage.getEnemy2().getX() >= 291
						&& stage.getEnemy2().getY() >= 412 && stage.getEnemy2().getY() <= 468) {
					stage.getEnemy2().enemyStart();
				} else {
					if (stage.getEnemy2().getDirection() == 0) {
						stage.getEnemy2().left();
					} else if (stage.getEnemy2().getDirection() == 1) {
						stage.getEnemy2().right();
					} else if (stage.getEnemy2().getDirection() == 2) {
						stage.getEnemy2().up();
					} else {
						stage.getEnemy2().down();
					}
				}

				if (stage.getEnemy3().getX() <= 415 && stage.getEnemy3().getX() >= 291
						&& stage.getEnemy3().getY() >= 412 && stage.getEnemy3().getY() <= 468) {
					stage.getEnemy3().enemyStart();
				} else {
					if (stage.getEnemy3().getDirection() == 0) {
						stage.getEnemy3().left();
					} else if (stage.getEnemy3().getDirection() == 1) {
						stage.getEnemy3().right();
					} else if (stage.getEnemy3().getDirection() == 2) {
						stage.getEnemy3().up();
					} else {
						stage.getEnemy3().down();
					}
				}
//				stage.getPlayer().setLeft(true);
//				stage.getPlayer().setRight(true);
//				stage.getPlayer().setUp(true);
//				stage.getPlayer().setDown(true);
				dispose();
			}

		});
	}

	static class DrawLogo extends JPanel {
		private Image logo;

		public DrawLogo() {

			JLabel label = new JLabel();
			add(label);
		}

		@Override
		public void paint(Graphics g) {

			super.paint(g);

			g.setFont(new Font("DungGeunMo", Font.BOLD, 30));
			g.setColor(new Color(255, 255, 255));
			g.drawString("게임을 재시작하려면 클릭해주세요!", 140, 450);
			g.setFont(new Font("DungGeunMo", Font.BOLD, 25));
			g.drawString("Pause Game!", 300, 500);

		}

	}

}
