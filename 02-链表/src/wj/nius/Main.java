package wj.nius;

public class Main {

	public static void test() {
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
	
	public static void main(String[] args) {
		System.out.println("第 8 节");
		
		test();
		
	}

}
