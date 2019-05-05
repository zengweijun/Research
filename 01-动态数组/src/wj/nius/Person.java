package wj.nius;

public class Person {
	private int age;
	private String name;
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "{Person：age=" + age + ", name=" + name + "}";
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		
		System.out.println("Person - finalize");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Person) {
			// 此处名字相同视为同相等
			Person person  = (Person) obj;
			return this.name.equals(person.name);
		}
		return false;
	}
}
