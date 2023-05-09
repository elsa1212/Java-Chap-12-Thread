package ex02;

import java.lang.Thread.State;

public class ThreadMain {

	public static void main(String[] args) {
		// 현재 실행 중인 스레드
		Thread th = Thread.currentThread(); // 현재 실행중인 Thread
		long id = th.getId();
		System.out.println("id: " + id); // main Thread는 id 1번 고정!!
		String name = th.getName();
		System.out.println("name: " + name);
		
		// priority : 1 ~ 10 
		int pri = th.getPriority();
		System.out.println("priority: " + pri);
		State state = th.getState();
		System.out.println("state: " + state);
	}

}
