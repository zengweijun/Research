package com.nius.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.nius.utils.JdbcDaoUtils;

public class Test {
// 结论:
//	1.MyISAM:效率比InnoDB高，但是不支持外键和支持事务
//	2.MySql原本不支持批处理，后来版本的MySql可以添加url参数`rewriteBatchedStatements=true`
//	  对PreparedStatement支持批处理优化，最新版好像不用了（有待验证：自己验证过，但不确定）
	
// MySQL服务器既不支持PreparedStatement的性能优化,也不支持JDBC中的批量操作优化.
// 但是,在新的JDBC驱动中,我们可以通过设置参数来支持批处理操作.
// 对PreparedStatement的批处理有效.
// url=jdbc:mysql://localhost:3306/jdbcdemo?rewriteBatchedStatements=true

	
//	------------------------ 未使用批量操作 ---------------------------
	// 1.Statement
	// InnoDB引擎耗时：2051ms
	// MyISAM引擎耗时：1629ms
	@org.junit.Test
	public void test1() throws SQLException {
		Connection cnn = JdbcDaoUtils.getConnection();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			String sql = "insert into t_performance (name, age) values ('AA', 12)";
			Statement st = cnn.createStatement();
			st.executeUpdate(sql);
			JdbcDaoUtils.close(null, st, null);
		}
		System.out.println(System.currentTimeMillis() - begin);
		JdbcDaoUtils.close(cnn, null, null);
	}

	// 2.PreparedStatement
	// InnoDB引擎耗时：2288ms
	// MyISAM引擎耗时：1768ms
	@org.junit.Test
	public void test2() throws SQLException {
		Connection cnn = JdbcDaoUtils.getConnection();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			String sql = "insert into t_performance (name, age) values (?, ?)";
			PreparedStatement st = cnn.prepareStatement(sql);
			st.setString(1, "AA");
			st.setInt(2, 12);;
			st.executeUpdate();
			JdbcDaoUtils.close(null, st, null);
		}
		System.out.println(System.currentTimeMillis() - begin);
		JdbcDaoUtils.close(cnn, null, null);
	}
	
//	------------------------ 使用批量操作 ---------------------------
	// 1.Statement
	// InnoDB引擎耗时：1559ms
	// MyISAM引擎耗时：1011ms
	@org.junit.Test
	public void test3() throws SQLException {
		Connection cnn = JdbcDaoUtils.getConnection();
		long begin = System.currentTimeMillis();
		Statement st = cnn.createStatement();
		for (int i = 0; i < 5000; i++) {
			String sql = "insert into t_performance (name, age) values ('AA', 12)";
			st.addBatch(sql); // 把sql添加到批量容器中
			if (i % 200 == 0) { // 每200条作为一批
				st.executeBatch();  // 执行
				st.clearBatch();  // 清空批处理缓存
			}
		}
		System.out.println(System.currentTimeMillis() - begin);
		JdbcDaoUtils.close(cnn, st, null);
	}

	// 2.PreparedStatement
	// InnoDB引擎耗时：63ms
	// MyISAM引擎耗时：51ms
	@org.junit.Test
	public void test4() throws SQLException {
		Connection cnn = JdbcDaoUtils.getConnection();
		long begin = System.currentTimeMillis();
		String sql = "insert into t_performance (name, age) values (?, ?)";
		PreparedStatement st = cnn.prepareStatement(sql);
		for (int i = 0; i < 5000; i++) {
			st.setString(1, "AA");
			st.setInt(2, 12);
			st.addBatch(); // 添加到批处理容器中
			if (i % 200 == 0) {
				st.executeUpdate(); // 执行
				st.clearBatch(); // 清空批处理缓存
				st.clearParameters(); // 清除参数
			}
		}
		System.out.println(System.currentTimeMillis() - begin);
		JdbcDaoUtils.close(cnn, st, null);
	}	
}
