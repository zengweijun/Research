package com.Net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class URL_URI {

	// 组成(四部分)
	// 协议 + 域名 + :端口(默认80) + 资源名(资源路劲)
	// http + www.baidu.com(:80) + newspage/data/landingsuper
	// https://mbd.baidu.com/newspage/data/landingsuper?context=1
	// 上边四部分之后，可以使用？添加参数 
	
	// URL:统一资源定位符，指向互联网资源的指针
	
	public static void main(String[] args) {
//		test1();
//		test2();
		test3();
		test4();
	}
	
	// 创建URL
	public static void test1() {
		try {
			// 绝对路径构建(端口80可以省略，默认)
			//URL url = new URL("http://www.baidu.com:80/index.html");
			URL url = new URL("http://www.baidu.com/index.html");
			System.out.println(url);
			
			// 相对路劲构建
			URL url2 = new URL(new URL("http://www.baidu.com:80/"), "index.html");
			System.out.println(url2);
			//URL url3 = new URL("http", "www.baidu.com:80", "index.html");
			//System.out.println(url3);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void test2() {
		try {
			//URL url = new URL("http://www.baidu.com:80/index.html?#aa&uname=曾维俊");
			URL url = new URL("http://www.baidu.com:80/index.html?uname=曾维俊");
			System.out.println("url：" + url);
			System.out.println("协议：" + url.getProtocol());
			System.out.println("主机：" + url.getHost());
			System.out.println("端口：" + url.getPort());
			System.out.println("文件路劲：" + url.getPath());
			System.out.println("参数：" + url.getQuery());
			System.out.println("文件：" + url.getFile());
			System.out.println("锚点：" + url.getRef()); // 锚点和参数是冲突的，职能有一个
//			System.out.println("url：" + url.getPath());
			
			url = new URL("http://www.baidu.com:80/a/");
			url = new URL(url,"b.txt"); // 相对路径构建
			System.out.println(url.toString());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	// 爬虫百度
	public static void test3() {
		try {
			URL url = new URL("http://www.baidu.com");
			
			/* 这种方式会有乱码，字符流
			InputStream is = url.openStream();
			byte[] bytes = new byte[1024]; // 中转容器
			int len = 0;
			while (-1 != (len = is.read(bytes))) {
				System.out.println(new String(bytes));
				//System.out.println(new String(bytes, 0, len));
			}
			is.close();
			*/
			
			// 字符流可以指定编码集
			InputStreamReader isr = new InputStreamReader(url.openStream(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("testbaid.html"), "utf-8"));
			
			String msg = null;
			while (null != (msg = br.readLine())) {
				// System.out.println(msg);
				bw.append(msg);
				bw.newLine();
			}
			bw.close();
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 爬虫百度2
	public static void test4() {
		try {
			URL url = new URL("http://www.baidu.com");
			
			// 字符流可以指定编码集
			InputStreamReader isr = new InputStreamReader(url.openStream(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("testbaid.html"), "utf-8"));
			
			String msg = null;
			while (null != (msg = br.readLine())) {
				// System.out.println(msg);
				bw.append(msg);
				bw.newLine();
			}
			bw.close();
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
