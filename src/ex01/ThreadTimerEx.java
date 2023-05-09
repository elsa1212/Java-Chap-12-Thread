package ex01;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ThreadTimerEx extends JFrame {
	Container c = getContentPane();
	JLabel label = new JLabel("0");
	
	
	/* JFrame Constructor */
	public ThreadTimerEx() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Thread Timer");
		setSize(200, 200);
		
		setLabel();
		
		setVisible(true);
		
		TimerThread th = new TimerThread(); 
		// th.run();
		th.start(); // TimerThread에 해당하는 stack을 준비 -> run()을 알아서 실행.
		
		// for 끝난 이후에 실행됨.
		System.out.println("생성자 종료.");
	}
	
	public void setLabel() {
		label.setFont(new Font("Consolas", Font.BOLD, 50));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		c.add(label);
	}
	
	public class TimerThread extends Thread {
		// Thread의 Main method
		
		@Override
		public void run() {
			System.out.println("run 실행됨.");
			for (int i = 1; i <= 10; i++) {
				label.setText(String.valueOf(i));
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
		new ThreadTimerEx();
	}

}
