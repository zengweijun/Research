package com.nius.foundation.thread;

public class Rabbit implements Runnable {
	
	public static void main(String[] args) {
		test1();
	}
	
	public static void test1() {
		System.out.println("====================");
		
		
		
		Rabbit r1 = new Rabbit();
		Thread t1 = new Thread(r1);
		t1.start();
		
		// 不要手动调用run，start告诉cpu准备好了，至于什么时候调用由cpu决定
		// 到了合适的时机，cpu会主动执行run方法
		// t1.run(); 
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					System.out.println("run2-->" + Thread.currentThread() + "  :" + i);
				}
			}
		}).start();;
//		t2.start();
		
		System.out.println("test1-->" + Thread.currentThread());
		
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("run1-->" + Thread.currentThread() + "  :" + i);
		}
		
	}
}
