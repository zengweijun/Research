package com.nius.foundation.IO.文件分割合并;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		File src = new File("./src/com/nius/IO/test/33333333.jpg");
		FileInputStream is = new FileInputStream(src);
		
		byte[] bytes = new byte[1024];
		
		while (-1 != is.read(bytes)) {
			for (int i = 0; i < bytes.length; i++) {
				Byte b = new Byte(bytes[i]);
				System.out.print(IntegerConvert.byteToHexString(b));
			}
		}
	}
}

class IntegerConvert {
//	public static void main(String[] args) {
//		// 2进制0b开头（0b00010000）--> 10进制（16）--> 16进制（0x10）
//		byte b = 0b00010000; 
//		
//		System.out.println("0b 0001 0000" + "  2进制:  " + Integer.toBinaryString(b));
//		System.out.println("0b 0001 0000" + "  10进制: " + b);
//		System.out.println("0b 0001 0000" + "  16进制: " + Integer.toHexString(b));
//		
//		int mask = 0b1111; // 0xf
//		int b1 = b & mask;
//		System.out.println("0b 0001 0000" + "  取后4位: " + Integer.toHexString(b1));
//
//		int b2 = b>>4 & mask;
//		System.out.println("0b 0001 0000" + "  取前4位: " + Integer.toHexString(b2));
//		
//		
//		System.out.println("byteToHexString" + byteToHexString(b));
//	}
	
	private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', 
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	public static String byteToHexString(byte b) {
		// byte 一个字节，占8位，取前四位和后四位
		// 0b00010000 --> 16 or 0x10
		
		// 取4位运算符
		int mask = 0xf; // 0b1111
		char[] buf = new char[2];
		int b2 = b & mask; // 后四位
		int b1 = (b >> 4) & mask; // 前四位
		buf[0] = HEX_CHARS[b1]; 
		buf[1] = HEX_CHARS[b2]; 
        return new String(buf);
	}
}
