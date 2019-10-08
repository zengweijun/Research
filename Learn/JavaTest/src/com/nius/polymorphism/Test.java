package com.nius.polymorphism;

public class Test {
	public static void main(String[] args) {
		
		Person p1 = new Person();
		Person p2 = new Student();
		
		p1.create();
		p2.create();
		
		p1.test2();
		System.out.println(p2.sex);
		System.out.println(((Student)p2).szz);
		
		Student s1 = (Student)p2;
		s1.testInterface2();
		
		s1.testInterface3();
		YellowMan.testInterface1();
	}
}
