package com.wj.Inner_class.local_class;

public class Person {
	// 方法
	public void test() {
		class A {			
		}
		
		for (int i = 0; i < 10; i++) {
			class B {
			}
		}
		if (true) {
			class B {
			}
		}
	}
	
	// 初始化块（会拷贝到构造函数中，位于其它源代码之前）
	{
		class A {
			
		}
	}
	
	// 类初始化，jvm第一次使用该类，类似一次性函数
	static {
		class A {
			
		}
	}
}
