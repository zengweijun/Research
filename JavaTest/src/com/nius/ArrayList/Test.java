package com.nius.ArrayList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;
import java.util.function.Consumer;

public class Test {

	public static void main(String[] args) {
		testTreeSet();
	}
	
	public static void testTreeSet() {
		Person p1 = new Person(10, "名字1");
		Person p2 = new Person(11, "名字2");
		Person p3 = new Person(13, "名字3");
		Person p4 = new Person(15, "名字4");
		
		TreeSet<Person> treeSet = new TreeSet<Person>();
		treeSet.add(p1);
		treeSet.add(p2);
		treeSet.add(p3);
		treeSet.add(p4);
		
		Iterator<Person> iterator = treeSet.iterator();
		while (iterator.hasNext()) {
			System.out.println("---" + iterator.next());
		}
		
		Vector<Person> vector = new Vector<Person>();
		vector.add(p1);
		vector.add(p2);
		vector.add(p3);
		vector.add(p4);
		treeSet.forEach(new Consumer<Person>() {
			public void accept(Person p) {
				System.out.println("+++" + p);
			}
		});
	}
	
	public void testArrayList() {
		Person p1 = new Person(10, "名字1");
		Person p2 = new Person(11, "名字2");
		Person p3 = new Person(13, "名字3");
		Person p4 = new Person(15, "名字4");
		
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		System.out.println(list);
		list.sort(new Person.PersonComparator());
		System.out.println(list);
	}
	
	static class Person implements Comparable<Person> {
		int age = 0;
		String name = "";
		public Person(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}
		
		@Override
		public String toString() {
			return  name + "-" + age;
		}
		
		static class PersonComparator implements Comparator<Person> {
			public int compare(Person o1, Person o2) {
				return -o1.age + o2.age;
			}
		}

		public int compareTo(Person o) {
			return age - o.age;
		}
	}
}
