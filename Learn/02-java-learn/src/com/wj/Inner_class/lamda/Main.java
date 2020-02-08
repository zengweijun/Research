package com.wj.Inner_class.lamda;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
		
		// 注意：lamda没有引入新的作用域
		// 即lamda内部，使用this指针，指代的是所处方法的实例
		// 而匿名类内部使用this，指代的是当前匿名类实例
		// lamda底层虽然通过匿名类实现，但是在lamda表达式内使用this成员不会指代lamda实例，仍然使用外部实例
		
		// 1.匿名类写法
		test(new Runable() {
			@Override
			public void run() {
				System.out.println("匿名类写法");
			}
		});
		
		// 2.lamda写法
		test(()->{
			System.out.println("lamda写法");
		});
		
		// 3.只有一条语句时，可省略大括号、分号、return
		// 只有一个参数时，可省略参数两边的括号，无参时不能省略括号
		test(()->System.out.println("lamda写法"));
		
		/*********************************************
		 * 排序写法简化
		 *********************************************/
		Integer[] arr = {1 ,2};
		Arrays.sort(arr, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		}); // 原写法
		Arrays.sort(arr, (Integer o1, Integer o2)-> {
			return o1 - o2;
		}); // 闭包
		Arrays.sort(arr, (o1, o2)-> {
			return o1 - o2;
		}); // 省略参数类型(自动可通过类型推断)
		Arrays.sort(arr, (o1, o2)-> o1 - o2); // 单条语句省略大括号、分号、return
		
		//4.可以保留lamda
		Comparator<Integer> comparator = (o1, o2)-> o1 - o2;
		Arrays.sort(arr, comparator); // 单条语句省略大括号、分号、return
		
		// 5.有时间研究一下java方法引用，配合lamda使用代码更精简
	}
	
	static void test(Runable run) {
		run.run();
	} 
}
