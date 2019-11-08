package com.nius.foundation.file;

import java.io.File;
import java.util.Stack;

public class FileInfos {

	public static void main(String[] args) {
		System.out.println("该目录下有：" + fileCount("/Users/ybf/Desktop/test11") + "个文件");
	}
	
	public static int fileCount(String dir) {
		System.out.println("---------> begin");
		if (dir == null) { System.err.println("目录为空"); return 0; }
		
		File f = new File(dir);
		if (f.isFile()) { System.err.println(dir + "是一个文件"); return 1; }
		
		String[] list = f.list(); 
		Stack<String[]> stack = new Stack<String[]>();
		stack.push(list);
		
		int fileCount = 0;
		String prefix = "";
		while (!stack.isEmpty()) {
			String[] tmpList = stack.pop();
			prefix = prefix + "│    ";
			for (int i = 0; i< tmpList.length; i++) {
				String filename = tmpList[i];
				String filepath = dir + "/" + filename;
				File tmpFile = new File(filepath);
				
				String tag = "├──";
				if (i == tmpList.length - 1) {
					tag = "└──";
				}
				
				String fileType = "";
				if (tmpFile.isDirectory()) {
					stack.push(tmpFile.list());
					fileType = "【目录】";
				} else if (tmpFile.isFile()) {
					fileCount++;
					fileType = "【文件】";
				} else {
					fileType = "【这个是什么鬼？？？？】";
				} 
				System.out.println(prefix + tag + filepath + fileType);
			}
		}
		
		System.out.println("---------> done");
		return fileCount;
	}
}
