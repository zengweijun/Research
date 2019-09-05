package com.nius.polymorphism;

public class Person extends Human {
	public int sex = 1;
	public void create() {
		System.out.println("Person -- create");
		doGet();
	}
	
	public void doGet() {
		System.out.println("Person -- doGet");
	}

	@Override
	public void test1() {
		System.out.println("Person -- test1");
	}
}
