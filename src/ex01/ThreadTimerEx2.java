package ex01;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ThreadTimerEx2 extends JFrame {
	Container c = getContentPane();
	TimerLabel lblTimer = new TimerLabel();
	TimerLabel lblTimer2 = new TimerLabel();
	
	/* JFrame Constructor */
	public ThreadTimerEx2() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Thread Timer");
		setSize(200, 200);
		
		setLabel();
		
		setVisible(true);
		
		Thread th = new Thread(lblTimer);  
		Thread th2 = new Thread(lblTimer2);  
		// th.run();
		th.start(); // TimerThread에 해당하는 stack을 준비 -> run()을 알아서 실행.
		th2.start();
		// for 끝난 이후에 실행됨.
		System.out.println("생성자 종료.");
	}
	
	public void setLabel() {
		lblTimer.setFont(new Font("Consolas", Font.BOLD, 50));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		c.add(lblTimer);
		c.add(lblTimer2,BorderLayout.SOUTH);
	}
	
	public class TimerLabel extends JLabel implements Runnable {
		// Thread의 Main method
		
		@Override
		public void run() {
			System.out.println("run 실행됨.");
			for (int i = 1; i <= 10; i++) {
				this.setText(String.valueOf(i));
				// delay(1000)	
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		new ThreadTimerEx2();
	}

}
