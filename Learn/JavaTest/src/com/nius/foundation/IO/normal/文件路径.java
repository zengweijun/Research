
package com.nius.foundation.IO.normal;
import java.io.File;

public class 文件路径 {
	public static void main(String[] args) {
		test2();
	}
	
	// 文件路径构造
	public static void test1() {
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);
		
		// 构建一个路径
		// 绝对路径
		String abpath1 = "/Users/ybf/Documents/Algorithm/JavaTest/"; 
		
		// 灵活路径(当路劲需要动态配置的时候使用)
		// windows上是反斜杠“\”，不过“/”在java中通用
		String abpath2 = File.separator + "Users" + File.separator + 
				"ybf" + File.separator + 
				"Documents" + File.separator + 
				"Algorithm" + File.separator + 
				"JavaTest" + File.separator;
		
		// 相对路径(推荐方式)
		String path = "./src/com/nius/IO/test";
		
		System.out.println(new File(".").getAbsolutePath());
	}
	
	// 获取路径、文件名等
	public static void test2() {
		String abpath = "/Users/ybf/Documents/Algorithm/JavaTest/"; 
		String path = "./src/com/nius/IO/test";
		File f1 = new File(abpath);
		File f2 = new File(path);
		
		System.out.println("AbsolutePath：" + f1.getAbsolutePath());
		System.out.println("Name：" + f1.getName());
		System.out.println("Parent：" + f1.getParent());
		System.out.println("Path：" + f1.getPath());
		System.out.println("isAbsolute：" + f1.isAbsolute());
		
		System.out.println("============================");

		System.out.println("AbsolutePath：" + f2.getAbsolutePath());
		System.out.println("Name：" + f2.getName());
		System.out.println("Parent：" + f2.getParent());
		System.out.println("Path：" + f2.getPath());
		System.out.println("isAbsolute：" + f2.isAbsolute());
	}
}
