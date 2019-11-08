package com.nius.foundation.polymorphism;

public class Student extends Person implements YellowMan {

	@Override
	public void doGet() {
		super.doGet();
		System.out.println("Student -- doGet");
	}

	@Override
	public void testInterface3() {
		System.out.println("Student -- testInterface3");
		
	}
}
