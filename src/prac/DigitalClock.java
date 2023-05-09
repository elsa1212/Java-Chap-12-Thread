package prac;

import java.awt.Container;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DigitalClock extends JFrame {
	Container c = getContentPane();
	ClockLabel lblClock = new ClockLabel("00:00:00");
	
	/* JFrame Constructor */
	public DigitalClock() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Digital Clock");
		setSize(500, 500);
		
		/* setUI */
		setLabel();
		
		/* setThread */
		Thread thClock = new Thread(lblClock);
		thClock.start();
		
		setVisible(true);
	}
	
	public void setLabel() {
		lblClock.setFont(new Font("Consolas", Font.BOLD, 50));
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		c.add(lblClock);
	}
	
	public class ClockLabel extends JLabel implements Runnable {

		public ClockLabel(String text) {
			super(text);
		}
		
		@Override
		public void run() {
			
			while(true) {						
				String clockText = getNowClock();
				this.setText(clockText);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public String getNowClock() {
			String clockText = "";
			
			Calendar c = Calendar.getInstance(); // Calender Class -> Singleton
			
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int min = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);
			int[] hhmmss = {hour, min, second};
			int lastIndex = hhmmss.length - 1;
			
			for(int i = 0; i < hhmmss.length; i++) {
				clockText += addZero(hhmmss[i]);
				if(i != lastIndex) {					
					clockText += ":";
				}
			}
			
			return clockText;
		}
		
		public String addZero(int num) {
			String resultString = String.valueOf(num);
			if(num < 10) {
				resultString = "0" + resultString;
			}
			
			return resultString;
		}
		
	}
	
	public static void main(String[] args) {
		new DigitalClock();
	}

}
