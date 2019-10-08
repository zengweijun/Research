package com.nius.IO.BytesStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.IIOException;

import com.nius.IO.util.FileUtil1;
import com.nius.IO.util.FileUtil2;

public class CopyFile {
	public static void main(String[] args) {
		
		int testCount = 15;
		double time1 = 0;
		for (int i = 0; i < testCount; i++) {
			long startTime = new Date().getTime();
			test性能(true);
			long endTime = new Date().getTime();
			time1 += (endTime - startTime)/1000.;
		} 
		System.out.println("使用缓冲流平均时长：" + time1/testCount);
		
		System.out.println("=====================================");
		double time2 = 0;
		for (int i = 0; i < testCount; i++) {
			long startTime = new Date().getTime();
			test性能(false);
			long endTime = new Date().getTime();
			time2 += (endTime - startTime)/1000.;
		} 
		System.out.println("未用缓冲流平均时长：" + time2/testCount);
	}
	
	public static void test性能(boolean use) {
		// 采用缓冲流
		if (use) {
			String parent = "./src/com/nius/IO/test";
			try {
				System.out.println("开始拷贝");
				FileUtil2.copyDir(parent + "/abc", 
						parent + "/xxx1");
				System.out.println("结束拷贝");
			} catch (IIOException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String parent = "./src/com/nius/IO/test";
			try {
				System.out.println("开始拷贝");
				FileUtil1.copyDir(parent + "/abc", 
						parent + "/xxx2");
				System.out.println("结束拷贝");
			} catch (IIOException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
