package ex04;

public class SharedData {
	public static int num = 0;
	
	public static void main(String[] args) {
		MyThread th1 = new MyThread();
		MyThread th2 = new MyThread();
		th1.start();
		th2.start();
	}

}

class MyThread extends Thread {
	// Lock 
	private void calc() {
		int num = SharedData.num;
		num++;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SharedData.num = num;
		System.out.println(SharedData.num);
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			calc();
		}
	}
}