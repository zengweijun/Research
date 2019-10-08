package com.nius.thread;

public class MyThread {
	public static void main(String[] args) {
		
		TestThread t = new TestThread();
		t.start();

		// 匿名类实现Runnable创建线程
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 30; i++) {
					System.out.println(Thread.currentThread().getName() + "->" + i);
				}
			}
		});
		t2.start();
		
		Thread t3 = new Thread(new Test());
		t3.start();
		
		for (int i = 0; i < 30; i++) {
			System.out.println(Thread.currentThread().getName() + "->" + i);
		}
	}
}

// 实现Runnable创建线程
class Test implements Runnable {
	public void run() {
		for (int i = 0; i < 30; i++) {
			System.out.println(Thread.currentThread().getName() + "->" + i);
		}
	}
}

// 继承创建线程
class TestThread extends Thread {
	@Override
	public void run() {

		for (int i = 0; i < 30; i++) {
			System.out.println(Thread.currentThread().getName() + "->" + i);
		}
	}
}