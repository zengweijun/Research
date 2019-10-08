package com.Net;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class MyInetSocketAddress {
	public static void main(String[] args) {
		test1();
	}
	
	public static void test1() {
		InetSocketAddress add = new InetSocketAddress("100.95.227.210",9999);
		System.out.println(add.getHostName());
        System.out.println(add.getPort());
        InetAddress addr = add.getAddress(); // 获得端口的ip；
        System.out.println(addr.getHostAddress()); // 返回ip；
        System.out.println(addr.getHostName()); // 输出端口名；
	}
}
