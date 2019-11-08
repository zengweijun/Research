package com.nius.foundation.InnerClass;

import java.util.Arrays;
import java.util.Comparator;

public class Test {

	public static void main(String[] args) {
		Person1 p1 = new Person1(); 
		Person1.A a1 = p1.new A();
		// Person1.A a2 = new p1.A();
		a1.test();
		
		Person2.A a3 = new Person2.A();
		a3.test();
		
		test();
		
		
		// 匿名内部类
		Integer[] listIntegers = {2, 1, 4 ,3, 5};
		printArr(listIntegers);
		Arrays.parallelSort(listIntegers, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		printArr(listIntegers);
	}
	
	public static void printArr(Integer[] arr) {
		for(int value : arr) {
			System.out.print(value + " ");
		}
		System.out.println();
	}
	
	
	// 局部类
	// 不能声明静态相关
	public static void test() {
		int a = 20;
		
		class ABC {
			int age = 20;
			public void test() {
				System.out.println("ABC age + a" + age + a);
			}
		}
		
		ABC abc = new ABC();
		abc.test();
	}
	
}


class Person1 {
	int age = 10;
	
	void test1() {
		System.out.println("Person1 -- test1");
	}
	
	// 成员内部类
	// 不能声明static相关方法、属性
	public class A {
		int height = 20;
		
		void test(){
			System.out.println("A(static) -- test");
			test1();
			System.out.println(age + height);
		}
	}
}


class Person2 {
	int age = 10;
	static int age1 = 10;
	
	void test1() {
		System.out.println("Person1 -- test1");
	}
	
	static void test2() {
		System.out.println("Person1 -- test2");
	}
	
	// 静态内部类，只是作用于区别，无其他区别
	// 不能声明static相关方法、属性
	public static class A {
		static int a = 20;
		int height = 20;
		
		void test(){
			System.out.println("A -- test");
			test2();
			System.out.println(age1 + height);
		}
	}
}