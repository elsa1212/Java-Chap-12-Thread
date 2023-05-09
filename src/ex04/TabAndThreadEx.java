package ex04;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TabAndThreadEx extends JFrame {
	
	Container c = getContentPane();
	MyLabel bar = new MyLabel();
	ConsumeThread thConsume = new ConsumeThread();
	KeyAdapter adapter = new KeyAdapter() {

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Key pressed");
			bar.fill();
		}
		
	};
	
	int barSize = 290;
	
	public TabAndThreadEx() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("아무키나 빨리 눌러 바 채우기");
		setSize(350, 200);
		
		setContainer();
		setBar();
		setListener();
		
		thConsume.start();
		
		setVisible(true);
	}
	
	public void setContainer() {
		c.setLayout(null);
		c.setFocusable(true);
		c.requestFocus();
	}

	public void setBar() {
		bar.setSize(300, 20);
		bar.setLocation(20, 50);
		bar.setOpaque(true);
		c.add(bar);
	}
	
	public void setListener() {
		c.addKeyListener(adapter);
	}

	public class MyLabel extends JLabel {				
		
		public MyLabel() {
			setBackground(Color.ORANGE);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int lblX = getX();
			int lblY = getY();
			
			int barHeight = 20;
			
			g.setColor(Color.MAGENTA);
			g.fillRect(0, 0, barSize, barHeight);
			g.fillRect(lblX, lblY, barSize, barHeight);
		}
		
		public synchronized void fill() {
			System.out.println("fill, barSize: " + barSize);
			if(barSize == 300) {
				return;
			}
			barSize++;
			repaint();
			notify();
		}
		
		public synchronized void consume() {
			System.out.println("consume, barSize: " + barSize);
			if (barSize == 0) {
				try {
					wait();
					return;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			barSize--;
			repaint();
		}
	}
	
	public class ConsumeThread extends Thread {
		@Override
		public void run() {
			while(true) {		
				bar.consume();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new TabAndThreadEx();
	}

}
