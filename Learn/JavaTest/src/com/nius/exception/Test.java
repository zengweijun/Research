package com.nius.exception;

public class Test {
	public static void main(String[] args) {
		
		Test test = new Test();
		test.test1();
	}
	
	public void test1() {
		try {
			System.out.println("111111");
			test2();
			System.out.println("22222");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("收尾工作");
		}
	}
	
	public void test2() throws NullPointerException {
		throw new NullPointerException("哈哈哈");
	}
}
