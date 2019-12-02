package com.nius.tx;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.nius.utils.JdbcDaoUtils;

// 银行转账
// 特例：张无忌 给 赵敏 转账1000元
// 从张无忌账户上划走1000，此时突然断电，会造成张无忌少掉1000，而赵敏没有收到钱
// 事务的概念即是：一组操作全部成功才算成功，只要有一个失败就视为失败
// 事务内部操作需要满ACID原则：原子性、一致性、隔离性、持久性

public class TransferAccout {

	@Test
	public void test() throws SQLException {
		Connection cnn = JdbcDaoUtils.getConnection();

		// --------- 检查张无忌账户余额 --------
		// 这里假设用户名唯一，实际中可以使用身份证

		String sql1 = "select * from t_account where name = ? and balance >= ?;";
		PreparedStatement ps = cnn.prepareStatement(sql1);
		ps.setString(1, "张无忌");
		ps.setInt(2, 1000);

		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			throw new RuntimeException("张无忌账户余额不足");
		}

		// --------- 张无忌账户余额减少1000 --------
		String sql2 = "update t_account set balance = balance - ? where name = ?";
		ps = cnn.prepareStatement(sql2);
		ps.setInt(1, 1000);
		ps.setString(2, "张无忌");
		int effects1 = ps.executeUpdate();
		System.out.println((effects1 == 1) ? "张无忌转出成功" : "张无忌转出失败");

//		int i = 1 / 0; // 模拟断电、down机

		// --------- 赵敏账户余额增加1000 --------
		String sql3 = "update t_account set balance = balance + ? where name = ?";
		ps = cnn.prepareStatement(sql3);
		ps.setInt(1, 1000);
		ps.setString(2, "赵敏");
		int effects2 = ps.executeUpdate();
		System.out.println((effects2 == 1) ? "赵敏收到1000元成功" : "赵敏收到未1000元");

		JdbcDaoUtils.close(cnn, ps, rs);
	}

	// 使用事务方式
	// 默认情况下，jdbc对每一个statement自动包赚一个事务，自动提交
	@Test
	public void test1() throws SQLException {

		Connection cnn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cnn = JdbcDaoUtils.getConnection();
			// 默认情况下，jdbc对每一个statement自动包赚一个事务，自动提交
			// 这里将事务设置为手动提交方式
			cnn.setAutoCommit(false);

			// --------- 检查张无忌账户余额 --------
			// 这里假设用户名唯一，实际中可以使用身份证

			String sql1 = "select * from t_account where name = ? and balance >= ?;";
			ps = cnn.prepareStatement(sql1);
			ps.setString(1, "张无忌");
			ps.setInt(2, 1000);

			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new RuntimeException("张无忌账户余额不足");
			}

			// --------- 张无忌账户余额减少1000 --------
			String sql2 = "update t_account set balance = balance - ? where name = ?";
			ps = cnn.prepareStatement(sql2);
			ps.setInt(1, 1000);
			ps.setString(2, "张无忌");
			int effects1 = ps.executeUpdate();
			System.out.println((effects1 == 1) ? "张无忌转出成功" : "张无忌转出失败");

			int i = 1 / 0; // 模拟断电、down机

			// --------- 赵敏账户余额增加1000 --------
			String sql3 = "update t_account set balance = balance + ? where name = ?";
			ps = cnn.prepareStatement(sql3);
			ps.setInt(1, 1000);
			ps.setString(2, "赵敏");
			int effects2 = ps.executeUpdate();
			System.out.println((effects2 == 1) ? "赵敏收到1000元成功" : "赵敏收到未1000元");

			cnn.commit(); // 提交事务
		} catch (Exception e) {
			e.printStackTrace();
			
			// 注意：虽然这里即使不提交回滚，张无忌额钱也不会转出去，但是会导致该链接connect一直被锁住（共享锁）
			// 所以这里需要提交回滚操作，释放该锁
			try {
				cnn.rollback(); // 回滚
			} catch (Exception e2) {
				e.printStackTrace();
			}
			
		} finally {
			JdbcDaoUtils.close(cnn, ps, rs);
		}
	}
}
