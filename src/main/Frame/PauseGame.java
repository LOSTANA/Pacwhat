package main.Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PauseGame extends JFrame {

	private DrawLogo drawLogo;
	private Maingame stage;

	public PauseGame(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();
		addEventListener();

	}
	//화면 구성
	private void initData() {

		setSize(750, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true); // 위쪽 언더바 삭제
		setBackground(new Color(0, 0, 0, 122));

		drawLogo = new DrawLogo();

		setLayout(new BorderLayout());
		drawLogo.setBackground(new Color(255, 255, 255, 0));
	}
	//텍스트를 입히기 위한 세팅
	private void setInitLayout() {

		add(drawLogo, BorderLayout.CENTER);

		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}
	//이벤트 리스너(마우스, 키보드)
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
				dispose();
			}

		});

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE:

					if (stage.getPlayer().getState() == 1) {

						if (stage.getEnemy().getX() <= 415 && stage.getEnemy().getX() >= 291
								&& stage.getEnemy().getY() >= 412 && stage.getEnemy().getY() <= 468) {
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
							}
						
					 else if (stage.getPlayer().getState() == 2) {
						if (stage.getEnemy().getX() <= 415 && stage.getEnemy().getX() >= 291
								&& stage.getEnemy().getY() >= 412 && stage.getEnemy().getY() <= 468) {
							stage.getEnemy().enemyStart();
						} else {

							if (stage.getEnemy().getDirection() == 0) {
								stage.getEnemy().screamLeft();
							} else if (stage.getEnemy().getDirection() == 1) {
								stage.getEnemy().screamRight();
							} else if (stage.getEnemy().getDirection() == 2) {
								stage.getEnemy().screamUp();
							} else {
								stage.getEnemy().screamDown();
							}
						}
						if (stage.getEnemy2().getX() <= 415 && stage.getEnemy2().getX() >= 291
								&& stage.getEnemy2().getY() >= 412 && stage.getEnemy2().getY() <= 468) {
							stage.getEnemy2().enemyStart();
						} else {

							if (stage.getEnemy2().getDirection() == 0) {
								stage.getEnemy2().screamLeft();
							} else if (stage.getEnemy2().getDirection() == 1) {
								stage.getEnemy2().screamRight();
							} else if (stage.getEnemy2().getDirection() == 2) {
								stage.getEnemy2().screamUp();
							} else {
								stage.getEnemy2().screamDown();
							}
						}
						if (stage.getEnemy3().getX() <= 415 && stage.getEnemy3().getX() >= 291
								&& stage.getEnemy3().getY() >= 412 && stage.getEnemy3().getY() <= 468) {
							stage.getEnemy3().enemyStart();
						} else {

							if (stage.getEnemy3().getDirection() == 0) {
								stage.getEnemy3().screamLeft();
							} else if (stage.getEnemy3().getDirection() == 1) {
								stage.getEnemy3().screamRight();
							} else if (stage.getEnemy3().getDirection() == 2) {
								stage.getEnemy3().screamUp();
							} else {
								stage.getEnemy3().screamDown();
							}
						}
					}

					dispose();
					break;

				case KeyEvent.VK_ESCAPE:

					System.exit(0);
					break;

				}
			}
		});
	}
	// 글자 출력
	static class DrawLogo extends JPanel {

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
			g.drawString("Pause Game!", 320, 500);
			g.drawString("(ESC 클릭시 게임 종료)", 250, 540);

		}

	}

}
