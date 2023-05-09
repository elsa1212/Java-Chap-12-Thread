package ex03;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class VibratingFrame extends JFrame {
	Container c = getContentPane();
	MyThread th = new MyThread();

	public VibratingFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Stop Vibrating Frame");
		setSize(500, 500);
		
		th.start();
		c.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				/* Flag 이용한 Thread Terminate 권장 */
				// th.interrupt(); // InterruptedException 발생시킴.
				th.stopVibe(); // 강제로 멈추기 보다 멈추기를 요청.
			}
			
		});
		setVisible(true);
	}
	
	class MyThread extends Thread {
		boolean flag = true;
		
		/* public way to change a state of flag */
		public void stopVibe() {
			flag = false;
		}
		
		@Override
		public void run() {
			while(flag) {
				
				int x = (int)(Math.random() * 10) - 5;
				int y = (int)(Math.random() * 10) - 5;
				VibratingFrame.this.setLocation(x, y);
				// <review>
				// VibratingFrame -> Class 
				// this -> JComponent ( VibratingFrame extends JFrame )
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					System.out.println("진동 멈춤.");
					return; // 함수의 종료
					
				}
			}
			
			System.out.println("run 종료");
		}
	}
	
	public static void main(String[] args) {
		new VibratingFrame();
	}


}
