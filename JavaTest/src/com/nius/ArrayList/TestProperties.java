package com.nius.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.function.Consumer;

public class TestProperties {
	private static String filepath = "/Users/ybf/Desktop/test11/tmp.json";
	
	public static void main(String[] args) {
		test1();
		System.out.println("===================");
		test2();
	}
	
	public static void test2() {
		Properties properties = new Properties();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filepath);
			properties.load(inputStream);
			System.out.println(properties.get("111"));
			System.out.println(properties.get("115"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void test1() {
		Properties properties = new Properties();
		properties.setProperty("111", "值1");
		properties.setProperty("112", "值2");
		properties.setProperty("113", "值3");
		properties.setProperty("114", "值4");
		properties.setProperty("115", "值5");
		properties.setProperty("116", "值6");
		System.out.println(properties.get("111"));
		System.out.println(properties.get("115"));

		OutputStream out = null;
		try {
			// 文件后缀名不影响数据，值是建议使用什么软件打开作用
			out = new FileOutputStream(filepath);
			String comments = "测试1" + new Date().toString();
			properties.store(out, comments);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
