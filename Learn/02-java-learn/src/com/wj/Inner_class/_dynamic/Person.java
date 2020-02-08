package com.wj.Inner_class._dynamic;

public class Person {
	private int age = 10;
	public void show(One o) {
		System.out.println("Person age:" + age + ", no:" + o.no);
	}

	public class One {
		// 注意：只有到内部类One的实例挂掉以后，Person的实例才会挂掉，因为One实例实际上保留了Person的实例
		// 动态内部类存在一个隐形的Person实例，因此它必须依赖外部类的实例才有意义
		private int no = 30;
//		private int age = 20; 
		public void show() {
			// 这里的age，先看当前类this.age是否存在，如果存在使用this.age
			// 如果不存在this.age 则使用 this.person.age
			// 这个this.person此处可以用Person.this表达（语法糖）
			// Person.this.show(this);
			System.out.println("One    age:" + age + ", " + "no:" + no);
		}
	}
}
