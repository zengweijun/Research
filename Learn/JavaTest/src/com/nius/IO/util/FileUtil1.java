package com.nius.IO.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOException;

/**
 * 未使用缓冲流
 * @author 曾维俊
 *
 */
public class FileUtil1 {
	
	public static void copyDir(String srcPath, String destPath) throws IOException {
		File src = new File(srcPath);
		File dest = new File(destPath);
		System.out.println(src.getAbsolutePath() + "\n" + dest.getAbsolutePath());
		copyDir2(src, dest);
	}
	
	public static void copyDir2(File src, File dest) throws IOException {
		if (src.isFile()) {
			copyFile2(src, dest);
		} else if (src.isDirectory()) {
			dest.mkdirs();
			
			for (File f : src.listFiles()) {
				copyDir2(f, new File(dest, f.getName()));
			}
		}
	}
	
	public static void copyFile(String srcPath, String destPath) throws IOException {
		copyFile2(new File(srcPath), new File(destPath));
	}
	public static void copyFile2(File src, File dest) throws IOException {
		if (!src.isFile()) {
			throw new IIOException(src.getAbsolutePath() + "不是一个文件");
		}
		
		// 这里使用缓冲流，可以提高性能
		// FileInputStream、FileOutputStream 不使用缓冲流的时候，规定多少就每次读取、写入多少
		// BufferedInputStream、BufferedOutputStream 使用缓冲流的时候，系统可能会根据当前情况一次性读取更多直接到内存中
		// 从而减少硬盘访问次数，带来更高效率，通常推荐使用Buffered读取文件
		FileInputStream is = new FileInputStream(src);
		FileOutputStream os = new FileOutputStream(dest);
		
		// 读取、写入
		byte[] bytes = new byte[1024];
		int len = 0;
		while (-1 != (len = is.read(bytes))) {
			os.write(bytes, 0, len);
		}
		os.flush();
		
		// 先开启的后关闭
		os.close();
		is.close();
	}
}
