package main.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Maingame;
import main.Components.Enemy1;
import main.Components.Enemy2;
import main.Components.Enemy3;
import main.Components.Player;

public class BackgroundPlayerService3 implements Runnable {

	private BufferedImage image;
	private Player player;
	private Maingame stage;
	private Enemy1 enemy1;
	private Enemy2 enemy2;
	private Enemy3 enemy3;

	// 생성자 의존 설계
	public BackgroundPlayerService3(Player player, Enemy1 enemy1, Enemy2 enemy2, Enemy3 enemy3) {
		this.player = player;
		this.stage = player.getStage();
		this.enemy1 = enemy1;
		this.enemy2 = enemy2;
		this.enemy3 = enemy3;
		try {
			// 플레이어 캐릭터 기본 이미지 설정
			image = ImageIO.read(new File("img/background/BackgroundService2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		// 플레이어 state가 2일 때(=과일 아이템을 먹었을 때, 무적 상태)
		// 플레이어-에너미 좌표를 확인 후, 중첩 시 점수 UP
		while(player.getState()==2) {
			
			// for 문 반복문으로 0.2초x30번 반복(6초 동안 지속)
			for(int i=0; i<30; i++) {
				
				// 에너미 1 감지
				if(Math.abs((player.getX() + 28/ 2) - ( enemy1.getX() + 28 / 2)) < ( 28 / 2 + 28 / 2) &&
		                Math.abs( (player.getY() + 28 / 2) - (enemy1.getY() + 28 / 2)) < ( 28 /2 + 28 / 2)) {
					//System.out.println("적 공격 시작1");
					//System.out.println(player.getEatedCount());
					//System.out.println(player.getScore());				
		        	player.setEatedCount(player.getEatedCount()+100);
		        	
		        	// 에너미 2 감지
		        	} else if (Math.abs((player.getX() + 28/ 2) - ( enemy2.getX() + 28 / 2)) < ( 28 / 2 + 28 / 2) &&
		        			Math.abs( (player.getY() + 28 / 2) - (enemy2.getY() + 28 / 2)) < ( 28 /2 + 28 / 2)) {
		        		//System.out.println("적 공격 시작2");
		        		//System.out.println(player.getEatedCount());
						//System.out.println(player.getScore());	
		        		player.setEatedCount(player.getEatedCount()+100);
		        		
		        		// 에너미 3 감지
		        		} else if (Math.abs((player.getX() + 28/ 2) - ( enemy3.getX() + 28 / 2)) < ( 28 / 2 + 28 / 2) &&
				                Math.abs( (player.getY() + 28 / 2) - (enemy3.getY() + 28 / 2)) < ( 28 /2 + 28 / 2)) {
		        			//System.out.println("적 공격 시작3");
		        			//System.out.println(player.getEatedCount());
		    				//System.out.println(player.getScore());	
		        			player.setEatedCount(player.getEatedCount()+100);
		        			
				        	} else {
				        		
				        	}
			sleep(800);
			//System.out.println(i+"번째");
			}
			// 무적상태 (6초 후) 원래 상태로 복귀
			player.setState(1);
		}
	}
}