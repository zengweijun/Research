package com.nius.date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {
	public static void main(String[] args) {
		// 日期转字符串
		System.out.println(date2Str(new Date()));
		
		// 字符串转日期
		System.out.println(str2Date("2019-9-6"));
		
		testCalendar();
	}
	
	public static String date2Str(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z z");
		String dateString = df.format(date);
		return dateString;
	}
	
	public static Date str2Date(String dateStr) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static void testCalendar() {
		System.out.println("=========================");
		GregorianCalendar calendar = new GregorianCalendar();

		// calendar.setTime(new Date());
		// 2019-9-6 10:12
		// 注意：jdk中月份从0开始，因此这里的9月应该用参数8
		calendar.set(2019, 8, 6, 10, 12); // 2019-9-6 10:12
		
		System.out.println(calendar.getTime());
		
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		
	}
}
