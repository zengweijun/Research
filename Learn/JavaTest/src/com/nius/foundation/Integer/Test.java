package com.nius.foundation.Integer;

public class Test {
	public static void main(String[] args) {
		testInteger();
	}
	
	// 测试Integer
	public static void  testInteger() {
//		int a1 = 128;
//		int a2 = 128;
//		System.out.println(a3 == a4);
		
		// [-128, 127] 之间的数，虽然是Integer，但为了效率
		// 任然会使用基本数据类型（以基本数据类型对待）
		// == 比较两边是否相等
		// equals 比较两边内容是否相等
		Integer b1 = 127;
		Integer b2 = 127;
		System.out.println(b1 == b2);		// 基本数据类型，之比较值
		System.out.println(b1.equals(b2));  // 基本数据类型，之比较值
		
		Integer b3 = 128;
		Integer b4 = 128;
		System.out.println(b3 == b4);	    // 对象、不是同一个对象
		System.out.println(b3.equals(b4));  // 对象、值相等
	}
}
