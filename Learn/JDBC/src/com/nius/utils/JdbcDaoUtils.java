package com.nius.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcDaoUtils {
	// 连接数据库的四要素，不利于维护，如果将来改为oracle还需要改代码
	// 这里使用配置文件db.properties配置
	private static Properties properties = new Properties();
	
	// DaoUtils第一次被使用到的时候，且只执行一次
	// 这份字节码被加载进jvm的时候执行
	static { 
		try {
			// 加载和读取properties文件
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			properties.load(is);
			
			// 加载注册数据库
			Class.forName(properties.getProperty("driverClassName"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					properties.getProperty("url"), 
					properties.getProperty("username"),
					properties.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
