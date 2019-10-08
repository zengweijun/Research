package com.nius.sort.tools;

public class Asserts {
	public static void test(boolean value) {
		test(value, null);
	}

	public static void test(boolean value, String tag) {
		try {
			if (!value) throw new Exception("测试未通过");
			else if (null != tag && !tag.equals("")) System.out.println(tag + " 测试通过");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
