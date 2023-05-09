package oc;

import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Monster extends JLabel implements Runnable {
	private final int DELAY_TIME = 200;
	
	JPanel pnlGame; 
	JLabel lblAvatar;
	private int movement = 5;
	private boolean flag = true;
	
	public Monster(JPanel pnlGame, JLabel lblAvatar, String text) {
		super(text);
		this.pnlGame = pnlGame;
		this.lblAvatar = lblAvatar;
	}
	
	/* Getter and Setter */
	public boolean isFlag() {
		return flag;
	}

	public void setFlag (boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		while(flag) {
			// 1. 위치 이동.
			moveDirection();
			
			// 2. 2초 sleep
			try {
				Thread.sleep(DELAY_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	// 시계 방향 : 북 -> 북서 -> 서 -> 남서 -> 남 -> 남동 -> 동 -> 북동
	public void moveDirection() {
		int monsterX = this.getX();
		int monsterY = this.getY();
		
		int avatarX = lblAvatar.getX();
		int avatarY = lblAvatar.getY();
		
		// 오른쪽에 있음
		if (monsterX > avatarX) {
			monsterX += (movement * -1);
		} else {
			monsterX += movement;
		}
		
		if (monsterY > avatarY) {
			monsterY += (movement * -1);
		} else {
			monsterY += movement;
		}	
		
		this.setLocation(monsterX, monsterY);
	}
	

}
