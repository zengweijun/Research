package com.nius.bigdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nius.utils.JdbcDaoUtils;

public class Test {
	// 开发中不会直接将图片等二进制存入数据库，一般将图片存储在磁盘上，然后将路劲存入数据库
	// 这里直接存图片作为演示用
	
	@org.junit.Test
	public void test1() throws SQLException, FileNotFoundException {
		Connection cnn = JdbcDaoUtils.getConnection();
		String sql = "insert into t_bigdata (data) values (?)";
		PreparedStatement pst = cnn.prepareStatement(sql);
		InputStream is = new FileInputStream(new File("./resources/video.mp4"));
		pst.setBinaryStream(1, is);
		pst.executeUpdate();
		JdbcDaoUtils.close(cnn, pst, null);
	}
	
	@org.junit.Test
	public void test2() throws SQLException, IOException {
		Connection cnn = JdbcDaoUtils.getConnection();
		String sql = "select * from t_bigdata where id = 1";
		PreparedStatement pst = cnn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			InputStream inputStream = rs.getBinaryStream("data");
			// 写入文件
		}
		JdbcDaoUtils.close(cnn, pst, null);
	}
}
