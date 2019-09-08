package com.nius.file;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 树状打印目录
 * 目前任然有bug
 * @author 曾维俊
 *
 */
public class PrintFileList {
	public static void main(String[] args) {
		printDir("/Users/ybf/Desktop/文档");
//		printDir("/Users/ybf/Documents/YBF/ybf-compoents");
//		printDir("/Users/ybf/Desktop/test11");
		
	}
	
	public static void printDir(String filePath) {
		System.out.println("------>begin\n");
		String prefix = "";
		System.out.println("├──" + filePath);
		printDir(filePath, prefix);
		System.out.println("\n------>done");
	}
	
	public static void printDir(String filePath, String prefix) {
		File f = new File(filePath);
		String[] fileList = f.list();
		if (fileList == null) {
			System.out.println(prefix + filePath);
			return;
		}
		
		Arrays.sort(fileList, new Comparator<String>() {
			// 创建一个匿名内部类，语法：new 类/接口() { 类的具体实现 }
			// 事实上是一个接口或者类的匿名类实现
			@Override
			public int compare(String o1, String o2) {
				File inF1 = new File(filePath + "/" + o1);
				File inF2 = new File(filePath + "/" + o2);
				if (inF1.isFile() && inF2.isFile()) {
					return 0;
				}
				if (inF1.isFile() && !inF2.isFile()) {
					return -1;
				}
				if (!inF1.isFile() && inF2.isFile()) {
					return 1;
				}
				return inF1.list().length - inF2.list().length;
			}
		});
		
		prefix = prefix + "    ";
		for (int i = 0; i < fileList.length; i++) {
			String itemName = fileList[i];
			String itemPath = filePath + "/" + itemName;
			File f1 = new File(itemPath);
			if (f1.isHidden()) { continue; }
			
			String tag = " "+prefix+"├──"; // │
			if (i == fileList.length - 1) {
				tag = " "+prefix+"└──";
			} 
			String printName = tag + itemName;
//			if (itemName.startsWith("YBF")) {
				System.out.println(printName);
//			}
			if (f1.isFile()) { continue; }
			printDir(itemPath, prefix);
		}
	}
}
