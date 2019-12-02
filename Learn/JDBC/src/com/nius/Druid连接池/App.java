package com.nius.Druid连接池;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class App {
//	druid(德鲁伊)连接池:Java语言领域性能最好的数据库连接池(拷贝jar包:druid-1.0.15.jar).
//	https://github.com/alibaba/druid/wiki
//	使用起来非常类似于DBCP连接池.

	@Test
	public void test() throws SQLException {
		Connection cnn = DruidUtil.getConnection();
		System.out.println(cnn);
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			String sql = "insert into t_performance (name, age) values (?, ?)";
			PreparedStatement st = cnn.prepareStatement(sql);
			st.setString(1, "AA");
			st.setInt(2, 12);;
			st.executeUpdate();
		}
		System.out.println(System.currentTimeMillis() - begin);
	}
}
