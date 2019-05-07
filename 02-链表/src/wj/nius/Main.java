package wj.nius;

import wj.nius.both.BothCircleLinkList;
import wj.nius.single.LinkList;
import wj.nius.single.SingleCircleLinkList;

public class Main {

	public static void testSingle() {
		//List<Integer> list = new ArrayList<Integer>();
		List<Integer> list = new LinkList<Integer>();
		list.add(1); // 1
		list.add(2); // 1 2 
		list.add(3); // 1 2 3
		list.add(4); 
		list.add(5); 
		list.add(6); 
		list.add(7); 
		list.add(8); 
		list.add(9); 
		list.add(10); 
		System.out.println(list);
		list.add(8, 88); 
		System.out.println(list);
	}
	
	public static void testSingleCircle() {
		//List<Integer> list = new ArrayList<Integer>();
		List<Integer> list = new SingleCircleLinkList<Integer>();
		list.add(1); // 1
		list.add(2); // 1 2 
		list.add(3); // 1 2 3
		list.add(4); // 1 2 3 4
		list.add(5); // 1 2 3 4 5
		System.out.println(list);
		list.add(3, 33);  // 1 2 3 33 4 5
		System.out.println(list);
		list.add(0, 0);  // 0 1 2 3 33 4 5
		System.out.println(list);
		list.add(list.size(), 55);  // 0 1 2 3 33 4 5 55
		System.out.println(list);
		list.add(66);  // 0 1 2 3 33 4 5 55 66
		System.out.println(list);
		
		list.remove(4);
		System.out.println(list);
		

		list.remove(0);
		System.out.println(list);
		

		list.remove(list.size()-1);
		System.out.println(list);
	}
	
	public static void testBoth() {
		List<Integer> list = new wj.nius.both.LinkList<Integer>();
		list.add(1); // 1
		list.add(2); // 1 2 
		list.add(3); // 1 2 3
		list.add(4); // 1 2 3 4
		list.add(5); // 1 2 3 4 5
		System.out.println(list);
		list.add(3, 33);  // 1 2 3 33 4 5
		System.out.println(list);
		list.add(0, 0);  // 0 1 2 3 33 4 5
		System.out.println(list);
		list.add(list.size(), 55);  // 0 1 2 3 33 4 5 55
		System.out.println(list);
		list.add(66);  // 0 1 2 3 33 4 5 55 66
		System.out.println(list);
		
		list.remove(4);
		System.out.println(list);
		

		list.remove(0);
		System.out.println(list);
		

		list.remove(list.size()-1);
		System.out.println(list);
	}
	
	public static void testBothCircle() {
		List<Integer> list = new BothCircleLinkList<Integer>();
		list.add(1); // 1
		list.add(2); // 1 2 
		list.add(3); // 1 2 3
		list.add(4); // 1 2 3 4
		list.add(5); // 1 2 3 4 5
		System.out.println(list);
		list.add(3, 33);  // 1 2 3 33 4 5
		System.out.println(list);
		list.add(0, 0);  // 0 1 2 3 33 4 5
		System.out.println(list);
		list.add(list.size(), 55);  // 0 1 2 3 33 4 5 55
		System.out.println(list);
		list.add(66);  // 0 1 2 3 33 4 5 55 66
		System.out.println(list); 
		
		list.remove(4);// 0 1 2 3 4 5 55 66
		System.out.println(list);
		

		list.remove(0);// 1 2 3 4 5 55 66
		System.out.println(list);
		

		list.remove(list.size()-1); // 1 2 3 4 5 55
		System.out.println(list);
	}
	
	public static void main(String[] args) {
//		testSingle();
//		testSingleCircle();
//		testBoth();
		testBothCircle();
		
	}

}
