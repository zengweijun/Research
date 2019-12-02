package com.nius._01jdbc._01jdbc_链接;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
	// jdbc操作步骤：贾琏欲执事
	// 贾：加载（注册）驱动
	// 琏：链接数据库对象
	// 欲：创建语句
	// 执：执行sql
	// 事：释放资源
	
	public static void main(String[] args) {
		// 从jdbc4.0（java1.6）开始，可以不写 Class.forName，数据库厂商必须在jar包下包含以下目录
		// mysql-connector-java-8.0.18.jar -> META-INF -> services -> java.sql.Dirver
		// jdbc会自动读取该配置文件，自动注册，以下语句可以省略
		// Class.forName("com.mysql.cj.jdbc.Driver");
		// 注意：但是开发中不建议省略，javaWeb中不支持自动注册
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 主机名和端口号是localhost:3306时可以省略
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wjtest", "root",
					"admin123");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		while (true) {
		}
	}
}
