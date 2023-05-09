package ex02;

import java.lang.Thread.State;

class MyThread extends Thread {
	public MyThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		for(int i = 0; i <= 100; i++) {
			System.out.println(getName() + ": " + i);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ThreadMain2  {
	
	public static void main(String[] args) {
		MyThread th1 = new MyThread("Thread-1");
		MyThread th2 = new MyThread("Thread-2");
		MyThread th3 = new MyThread("Thread-3");
		
		th1.setPriority(Thread.MIN_PRIORITY); // 1
		th2.setPriority(Thread.NORM_PRIORITY); // 5
		th3.setPriority(Thread.MAX_PRIORITY); // 10
		
		th1.start();
		th2.start();
		th3.start();
	}

}
