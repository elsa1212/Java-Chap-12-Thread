package oc;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OpenChallange extends JFrame {
	final int LABEL_SIZE = 15;
	
	Container c = getContentPane();
	
	JPanel pnlGame = new JPanel();
	JLabel lblAvatar = new JLabel("@");
	Monster lblMonster = new Monster(pnlGame, lblAvatar, "M");
	
	MyKeyboardAdapter adapter = new MyKeyboardAdapter();
	Thread thMonster = new Thread(lblMonster);
	
	/* JFrame Consturctor */
	public OpenChallange() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Open Challange 12");
		setSize(500, 500);
		
		/* set UI */
		setLabel();
		setGamePanel();
		
		/* set Listener */
		pnlGame.addKeyListener(adapter);
		
		/* set Thread */
		thMonster.start();
		
		setVisible(true);
	}
	

	public void setLabel() {
		// lblAvatar
		lblAvatar.setForeground(Color.RED);
		lblAvatar.setSize(LABEL_SIZE, LABEL_SIZE);
		lblAvatar.setLocation(200, 200);
		
		// lblMonster
		lblMonster.setSize(LABEL_SIZE, LABEL_SIZE);
		lblMonster.setLocation(10, 10);
	}
	
	public void setGamePanel() {
		// pnlGame의 배치 관리자 삭제.
		pnlGame.setLayout(null);
		
		pnlGame.setFocusable(true);
		pnlGame.requestFocus();
		
		pnlGame.add(lblAvatar);
		pnlGame.add(lblMonster);
		
		c.add(pnlGame);
	}
	
	class MyKeyboardAdapter extends KeyAdapter {
		
		/* Avatar */	
		@Override
		public void keyPressed(KeyEvent e) {
			int pnlWidth = pnlGame.getWidth();
			int pnlHeight = pnlGame.getHeight();
			
			int maxX = pnlWidth - LABEL_SIZE;
			int maxY = pnlHeight - LABEL_SIZE;
			
			int x = lblAvatar.getX();
			int y = lblAvatar.getY();
			
			// left -> up -> right -> down
			int[] nx = {-10, 0, 10, 0};
			int[] ny = {0, -10, 0, 10};
			
			int targetKey = (int)e.getKeyCode();
			// ← : 37, ↑ : 38, → : 39, ↓ : 40
			// System.out.println("key pressed:" + e.getKeyCode());
			
			switch(targetKey) {
			case KeyEvent.VK_LEFT:
				x += nx[0];
				y += ny[0];
				if (x < 0) {
					x = 0;
				}
				break;
				
			case KeyEvent.VK_UP:
				x += nx[1];
				y += ny[1];
				if (y < 0) {
					y = 0;
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				x += nx[2];
				y += ny[2];
				if (x > maxX) {
					x = maxX;
				}
				break;
				
			case KeyEvent.VK_DOWN:
				x += nx[3];
				y += ny[3];
				if (y > maxY) {
					y = maxY;
				}
				break;
				
			case KeyEvent.VK_Q:
				System.exit(0);
				break;
			}
			lblAvatar.setLocation(x, y);
		}
	}
	
	public static void main(String[] args) {
		new OpenChallange();
	}

}
