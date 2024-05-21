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
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Startgame extends JFrame {

	private DrawLogo drawLogo;
	private Maingame stage;

	public Startgame() {

		initData();
		setInitLayout();
		addEventListener();

	}

	public Startgame(Maingame stage) {
		this.stage = stage;
		initData();
		setInitLayout();
		addEventListener();
		
	}

	private void initData() {

		setSize(750, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(stage == null) {
		drawLogo = new DrawLogo();
		}
		else
		{
			drawLogo = new DrawLogo(stage);
		}

		setLayout(new BorderLayout());
		drawLogo.setBackground(Color.BLACK);
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
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new Maingame();
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
					new Maingame();
					dispose();
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				
				
				}

			}
		});
	}

	static class DrawLogo extends JPanel {
		private Image logo;
		private Image pacman;
		private Image ghost1;
		private Image ghost2;
		private Image ghost3;
		private Maingame stage;

		public DrawLogo() {
			logo = new ImageIcon("img/pacman_logo.png").getImage();
			pacman = new ImageIcon("img/pacman/pac4_R.png").getImage();
			ghost1 = new ImageIcon("img/ghost1/cyanR2.png").getImage();
			ghost2 = new ImageIcon("img/ghost2/pinkR1.png").getImage();
			ghost3 = new ImageIcon("img/ghost3/redR3.png").getImage();

			JLabel label = new JLabel();
			add(label);
		}

		public DrawLogo(Maingame stage) {
			this.stage = stage;
			logo = new ImageIcon("img/pacman_logo.png").getImage();
			pacman = new ImageIcon("img/pacman/pac4_R.png").getImage();
			ghost1 = new ImageIcon("img/ghost1/cyanR2.png").getImage();
			ghost2 = new ImageIcon("img/ghost2/pinkR1.png").getImage();
			ghost3 = new ImageIcon("img/ghost3/redR3.png").getImage();

			JLabel label = new JLabel();
			add(label);
		}

		@Override
		public void paint(Graphics g) {

			super.paint(g);

			g.drawImage(logo, 130, 161, 500, 161, null);
			g.drawImage(pacman, 230, 520, 50, 50, null);
			g.drawImage(ghost1, 310, 520, 50, 50, null);
			g.drawImage(ghost2, 390, 520, 50, 50, null);
			g.drawImage(ghost3, 470, 520, 50, 50, null);
			g.setFont(new Font("DungGeunMo", Font.BOLD, 30));
			g.setColor(new Color(255, 255, 0));
			g.drawString("게임을 시작하려면 클릭해주세요!", 140, 720);
			g.setColor(new Color(255, 255, 255));
			if (stage == null) {
				g.drawString("점수가 없습니다!", 250, 400);
				g.drawString("플레이 해서 최고점수에 도전하세요!!", 100, 450);
			} else {
				g.drawString("최근 플레이 점수 : " + stage.getPlayer().getFinalscore(), 200, 400);
			}
			g.setFont(new Font("DungGeunMo", Font.BOLD, 25));
			g.setColor(new Color(255, 255, 0));
			g.drawString("Click to play game!", 230, 850);

		}

	}

	public static void main(String[] args) {
		new Startgame();
	}

}
