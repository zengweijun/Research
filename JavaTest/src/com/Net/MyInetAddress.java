package com.Net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class MyInetAddress {
	public static void main(String[] args) {
		test1();
		test2();
	}
	
	public static void test1() {
		try {
			System.out.println(InetAddress.getLocalHost());
			System.out.println(InetAddress.getByName(""));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static void test2() {
		// 获取本机的InetAddress实例  
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println(address); //直接输出InetAddress对象  

			System.out.println();
			System.out.println("计算机名：" + address.getHostName());
			System.out.println("IP地址：" + address.getHostAddress());
			
			//获取字节数组形式的IP地址  
			byte[] bytes = address.getAddress();
			System.out.println("字节数组形式的Ip：" + Arrays.toString(bytes));

			// 根据机器名获取InetAddress实例  
			// getByName(IP地址/域名)
			InetAddress address2 = InetAddress.getByName("ybfdeiMac.local");
			System.out.println();
			System.out.println("计算机名：" + address2.getHostName());
			System.out.println("IP地址：" + address2.getHostAddress());
			
			InetAddress address3 = InetAddress.getByName("192.168.1.10");
			System.out.println();
			System.out.println("计算机名：" + address3.getHostName());
			System.out.println("IP地址：" + address3.getHostAddress());
			
			InetAddress address4 = InetAddress.getByName("");
			System.out.println();
			System.out.println("计算机名：" + address4.getHostName());
			System.out.println("IP地址：" + address4.getHostAddress());
			
			InetAddress address5 = InetAddress.getByName("www.baidu.com");
			System.out.println();
			System.out.println("计算机名：" + address5.getHostName());
			System.out.println("IP地址：" + address5.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
