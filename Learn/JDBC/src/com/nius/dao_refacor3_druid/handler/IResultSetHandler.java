package com.nius.dao_refacor3_druid.handler;

import java.sql.ResultSet;

// 结果集处理器，规范外部处理方式
public interface IResultSetHandler<T> {
	T handle(ResultSet rs) throws Exception;
}
