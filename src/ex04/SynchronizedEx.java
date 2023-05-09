package ex04;

public class SynchronizedEx {

	public static void main(String[] args) {
		SharedPrinter printer = new SharedPrinter();
		
		String[] text1 = {"Wise men say, ",					
				"only fools rush in ",					
				"But I can't help,",					
				"falling in love with you",					
				"Shall I stay? ",					
				"Would it be a sin?",					
				"If I can't help, ",					
				"falling in love with you"};
		
		String[] text2 = {"동해물과 백두산이 마르고 닳도록",						
				"하느님이 보우하사 우리나라 만세",						
				"무궁화 삼천리 화려강산,",						
				"대한 사람 대한으로 길이 보전하세",						
				"남산 위에 저 소나무, 철갑을 두른듯",						
				"바람 서리 불변함은 우리 기상일세",						
				"무궁화 삼천리 화려강산,",						
				"대한 사람 대한으로 길이 보전하세"};
		
		WorkerThread th1 = new WorkerThread(printer, text1);
		WorkerThread th2 = new WorkerThread(printer, text2);
		
		th1.start();
		th2.start();
	}
}

class WorkerThread extends Thread {
	SharedPrinter printer;
	String[] text;
	
	public WorkerThread(SharedPrinter printer, String[] text) {
		this.printer = printer;
		this.text = text;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < text.length; i++) {
			printer.print(text[i]);
		}
	}
}

// 하나의 문장씩 print! 
class SharedPrinter {
	// 여러 스레드 중 하나가 독점적으로 실행하도록 보장하는 영역, 나머지 스레드는 대기 : Synchronized
	public synchronized void print(String text) {
		for(int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			System.out.print(ch);
		}
		System.out.println();
	}
}
