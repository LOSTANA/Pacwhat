package main;

public class Maingame extends JFrame {

	private JLabel backgroundMap;
	private Player player;
	private Enemy enemy;

	public Maingame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {

		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addEventListener() {

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.left();
					break;
					
				case KeyEvent.VK_RIGHT:
					player.right();
					break;
					
				case KeyEvent.VK_UP:
					player.up();
					break;
				case KeyEvent.VK_DOWN:
					player.down();
					break;
					
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
			}
			key
		});
	}
	
	public static void main(String[] args) {
		new Maingame();
	}

}
