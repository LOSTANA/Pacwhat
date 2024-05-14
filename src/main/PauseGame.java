package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Startgame.DrawLogo;
import main.Components.Enemy1;

public class PauseGame extends JFrame {

	private Maingame stage;

	private JPanel panel1;

	private DrawLogo drawLogo;

	public PauseGame(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {

		setSize(750, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawLogo = new DrawLogo();

		setLayout(new BorderLayout());
		

	}

	private void setInitLayout() {

		setUndecorated(true);
		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		setBackground(new Color(0, 0, 0, 112));
		
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
				dispose();
				setVisible(false);

				if (stage.getEnemy().getDirection() == 0) {
					stage.getEnemy().left();
				} else if (stage.getEnemy().getDirection() == 1) {
					stage.getEnemy().right();
				} else if (stage.getEnemy().getDirection() == 2) {
					stage.getEnemy().up();
				} else {
					stage.getEnemy().down();
				}

				if (stage.getEnemy2().getDirection() == 0) {
					stage.getEnemy2().left();
				} else if (stage.getEnemy2().getDirection() == 1) {
					stage.getEnemy2().right();
				} else if (stage.getEnemy2().getDirection() == 2) {
					stage.getEnemy2().up();
				} else {
					stage.getEnemy2().down();
				}

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
				// TODO Auto-generated method stub

			}
		});
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE:
					dispose();
					setVisible(false);

					if (stage.getEnemy().getDirection() == 0) {
						stage.getEnemy().left();
					} else if (stage.getEnemy().getDirection() == 1) {
						stage.getEnemy().right();
					} else if (stage.getEnemy().getDirection() == 2) {
						stage.getEnemy().up();
					} else {
						stage.getEnemy().down();
					}

					if (stage.getEnemy2().getDirection() == 0) {
						stage.getEnemy2().left();
					} else if (stage.getEnemy2().getDirection() == 1) {
						stage.getEnemy2().right();
					} else if (stage.getEnemy2().getDirection() == 2) {
						stage.getEnemy2().up();
					} else {
						stage.getEnemy2().down();
					}

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

			}
		});
	}

	static class DrawLogo extends JPanel {

		public DrawLogo() {
			JLabel label = new JLabel("일시정지");
			label.setOpaque(true);
			label.setFont(new Font("DungGeunMo", Font.BOLD, 38));
			label.setForeground(Color.WHITE);
			label.setBackground(new Color(0, 0, 0, 112));
			
			
			add(label);
		}

	}

}
