package com.nius.foundation.sort;

public class 冒泡排序 {

	public static void main(String[] args) {
		
		// 冒泡排序思路
		// 1.每次会将最大或者最小数移动到最后一个
		// 2.下次比较的时候就可以不比较最后一个（因为已经确定最后一个是最大或者最小）
		// 3.一次循环1、2步骤，会发现最大或者最小数依次向后固定，需要比较的次数会越来越小
		// 参考：https://www.cnblogs.com/bigdata-stone/p/10464243.html
		int[] arr = {1, 5, 6, 7, 1, 2, 3, 8, 9, 9, 3, 4, 2, 1, 3, 1, 2};
		printArr(arr);
		sort2(arr);
//		Arrays.sort(arr);
		printArr(arr);
	}
	
	public static void sort(int[] arr) {
		// 边界判断
		if (arr == null || arr.length < 2) {
			return;
		}
		
		// 由于内外层的时间复杂度都近似为O(n)、因此O(n) * O(n) ≈ O(n^2)
		// 可以看出，该算法的时间复杂度为指数阶
		for (int i = 0; i < arr.length - 1; i++) { 
			// 外层是排序次数 O(n-1) ≈ O(n)
			
			// 第i次比较
			for (int j = 0; j < arr.length - 1 - i; j++) { 
				// 内层是具体需要比较的某些元素 O(n - 1 - i) ≈ O(n)
				// 这里
				
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
	}
	
	// 优化效率，优化后任然为O(n^2)
	public static void sort2(int[] arr) {
		// 边界判断
		if (arr == null || arr.length < 2) {
			return;
		}
		
		// 由于内外层的时间复杂度都近似为O(n)、因此O(n) * O(n) ≈ O(n^2)
		// 可以看出，该算法的时间复杂度为指数阶
		for (int i = 0; i < arr.length - 1; i++) { 
			// 外层是排序次数 O(n-1) ≈ O(n)
			
			boolean switched = false;
			
			// 第i次比较
			for (int j = 0; j < arr.length - 1 - i; j++) { 
				// 内层是具体需要比较的某些元素 O(n - 1 - i) ≈ O(n)
				
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					switched = true;
				}
			}
			
			if (!switched) {
				// 如果某次比较已经没有发生数据交换，说明已经排好序，无需再排
				// 解决问题 [2, 1, 3, 4, 5]
				// 上边数组实际上只需要一次就可以排序完成
				break; 
			}
		}
	}
	
	public static void printArr(int[] arr) {
		for(int value : arr) {
			System.out.print(value + " ");
		}
		System.out.println();
	}
}
