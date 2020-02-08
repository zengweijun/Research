package com.wj.Inner_class._dynamic;
import com.wj.Inner_class._dynamic.Person.One;

public class Main {
	public static void main(String[] args) {
		System.out.println("动态内部类必须依赖外部类实例");
		System.out.println("因为其内部实际上存在一个外部类实例成员");
		System.out.println("动态内部类只能声命 static final " + '"' + "编译时" + '"' + " 常量");
		System.out.println("除了编译时常量外不能声明其它的 static 变量或者方法");
		System.out.println("动态内部类可以使用外部类的private变量，外部类也可以使用它的private变量");
		
		
		Person person = new Person();
		One one = person.new One();
		person.show(one);
		one.show();
	}
}
