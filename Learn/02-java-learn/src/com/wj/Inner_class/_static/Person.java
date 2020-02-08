package com.wj.Inner_class._static;

public class Person {
	private int age = 10;
	private static String name = "jack";
	private static void run() {
		System.out.println("Person run");
	}
	public void show(One o) {
		System.out.println("Person age:" + age + ", no:" + o.no);
	}

	public static class One {
		public int no = 20;
		// 静态态内部类本质上就是一个顶级类（Top-Level class），只不过代码放在了另一个类中
		// 也就是说，他的行为和一个普通的顶级类没有区别，只不过多了一些权限（对于外部类的访问）
		// 作为静态内部类，多一些特殊权限
		// 1.可以直接访问外部类中的类变量及类方法（无论是否为private）
		// 2.可以实例化外部类，通过实例访问外部类的实例变量和方法（无论是否为private）
		
		public void show() {
			// 直接访问外部类类变量和类方法（无论是否为private）
			run(); // 类方法
			String outClassNameVar = name; // 类变量
			
			// 访问外部类实例变量及方法（需要先创建实例，无论是否为private）
			Person person = new Person();
			int age = person.age; // 实例变量
			person.show(this);    // 实例方法
			
			System.out.println("One    age:" + age + ", " + "no:" + no);
		}
	}
}
