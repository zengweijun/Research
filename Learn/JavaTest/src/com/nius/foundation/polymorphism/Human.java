package com.nius.foundation.polymorphism;

public abstract class Human {
	String s = "123";
	int age = 123;
	
	public abstract void test1();
	public  void test2() {
		test1();
	}
}
