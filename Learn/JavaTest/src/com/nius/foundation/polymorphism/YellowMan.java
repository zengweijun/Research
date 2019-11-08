package com.nius.foundation.polymorphism;

public interface YellowMan {
	public String szz = "ss";
	
	public static void testInterface1() {
		System.out.println("YellowMan - testInterface1");
	}
	
	public default void testInterface2() {
		System.out.println("YellowMan - testInterface2");
	}
	
	public void testInterface3();
}
