package com.nius.dao_refacor3_druid.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nius.dao_refacor3_druid.handler.IResultSetHandler;

public class JdbcTemplate {

	/**
	 * DML操作（增删改模板）
	 * 
	 * @param sql    DML操作SQL模板，必须使用预编译型sql（带有占位符?）
	 * @param params SQL模板中?对应的参数（注意：必须一一对应）
	 * @return 受影响的行数
	 */
	public static int update(String sql, Object... params) {
		// 贾琏欲执事
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = JdbcDaoUtils.getConnection();
			ps = connection.prepareStatement(sql);
			for (int index = 0; index < params.length; index++) {
				// 第一个参数表示占位符位置，从1开始
				// 第二个参数取参数数组中的值
				ps.setObject(index + 1, params[index]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcDaoUtils.close(connection, ps, null);
		}
		return 0;
	}

	public static <T>T query(String sql, IResultSetHandler<T> resultSetHandler, Object... params) {
		// 贾琏欲执事
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcDaoUtils.getConnection();
			ps = connection.prepareStatement(sql);
			for (int index = 0; index < params.length; index++) {
				// 第一个参数表示占位符位置，从1开始
				// 第二个参数取参数数组中的值
				ps.setObject(index + 1, params[index]);
			}
			resultSet = ps.executeQuery();
			return resultSetHandler.handle(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcDaoUtils.close(connection, ps, resultSet);
		}
		return null;
	}

}
