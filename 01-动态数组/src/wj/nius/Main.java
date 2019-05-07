package wj.nius;

public class Main {

	static void test1() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1); // 1
		list.add(2); // 1 2 
		list.add(3); // 1 2 3
		list.add(1, 100); // 1 100 2 3 
//		 list.add(5, 100); // out of bounds 
		list.set(2, 250); // 1 100 250 3 
		list.add(40); // 1 100 250 3 40
		System.out.println(list);
		list.remove(2); // 1 100 3 40
		System.out.println(list);
		list.remove(0); // 100 3 40
		System.out.println(list);
		list.remove(0); // 3 40
		System.out.println(list);
		list.remove(0); // 40
		System.out.println(list);
		list.remove(0); // 
		System.out.println(list);
	}
	
	 public static void test2() {
		ArrayList<Person> list = new ArrayList<Person>();
		
		list.add(new Person(1, "维俊1"));
		list.add(new Person(2, "维俊2"));
		list.add(new Person(3, "维俊3"));
		list.add(new Person(4, "维俊4"));
		list.add(new Person(5, "维俊5"));
		list.add(new Person(6, "维俊6"));
		System.out.println(list);
		
		int index = list.indexOf(new Person(2, "郑媛4"));
		System.out.println("index = " + index);

		Person p = list.get(0);
		System.out.println("index = 0 is " + p);
		
		list.clear();
		System.out.println(list);
	}
	 
	 public static void test3() {
		 ArrayList<Object> list = new ArrayList<Object>();

			list.add(new Person(1, "维俊1"));
			list.add(1);
			list.add(null);
			list.add(null);
			list.add(4);
			list.add(new Person(6, "维俊6"));
			System.out.println(list);
			
			int index = list.indexOf(new Person(2, "郑媛4"));
			System.out.println("index = " + index);
			
			int index2 = list.indexOf(4);
			System.out.println("index2 = " + index2);
	}
	 
	 public static void test4() {
			ArrayList<Integer> list = new ArrayList<Integer>();
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
	 
	public static void test5() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			list.add(i);
		}
		
		while (list.size() != 0) {
			list.remove(0);
		}
		
	}
	
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
//		test4();
		test5();

	}

}
