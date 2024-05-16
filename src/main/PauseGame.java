package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PauseGame extends JFrame {

	private Maingame stage;

	private Panel panel1;

	public PauseGame(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		
		panel1 = new Panel();
		
		
		setSize(750, 1000);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("일시정지");
		panel1.setLayout(new BorderLayout());
		panel1.add(label,BorderLayout.CENTER);
		

		
		

	}

	private void setInitLayout() {

		add(panel1);
		setUndecorated(true);
		panel1.setBackground(new Color(0, 0, 0, 112));
		setLayout(new BorderLayout());
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

}
