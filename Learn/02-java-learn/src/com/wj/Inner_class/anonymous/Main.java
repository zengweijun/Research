package com.wj.Inner_class.anonymous;

public class Main {
	public static void main(String[] args) {
		// 格式： new 接口 { 类的定义 }
		// 匿名类只能定义编译时常量static final
		// 只能捕获final常量或者有效final常量
		// 特性类似局部类
		
		// 需要保留
		Runable person = new Runable() {
			@Override
			public void run() {
				System.out.println("Person1 - run");
			}
		};
		person.run();
		person.run();
		
		
		// 只用一次
		new Runable() {
			@Override
			public void run() {
				System.out.println("Person2 - run");
			}
		}.run(); // 直接run
		
		// 方法参数
		test(new Runable() {
			@Override
			public void run() {
				System.out.println("Person3 - run");
			}
		});
		
		// 闭包方式
		test(()-> {
			System.out.println("Person4 - run");
		});
	}
	
	static void test(Runable run) {
		run.run();
	}
}
