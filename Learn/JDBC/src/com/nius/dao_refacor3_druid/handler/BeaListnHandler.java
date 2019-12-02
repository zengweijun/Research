package com.nius.dao_refacor3_druid.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
  内省机制
  可以把不同表中的每一行数据,封装成不同类型的对象.
  注意/规范:
          1:规定表中的列名必须和对象中的属性名相同.
          2:规定表中列名的类型必须和Java中的类型要匹配.   
          decimal  ---->BigDecimal/ bigint------>Long
 */

public class BeaListnHandler<T> implements IResultSetHandler<java.util.List<T>> {
	private Class<T> classType;
	
	public BeaListnHandler(Class<T> classType) {
		this.classType = classType;
	}

	// 1:规定表中的列名必须和对象中的属性名相同.
    // 2:规定表中列名的类型必须和Java中的类型要匹配. 
	@Override
	public List<T> handle(ResultSet rs) throws Exception {
		List<T> list = new ArrayList<>();
 		while (rs.next()) {
			T obj = classType.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
			PropertyDescriptor[] pss = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor ps: pss) {
				String columnName = ps.getName();
				Object val = rs.getObject(columnName);
				ps.getWriteMethod().invoke(obj, val);
			}
			list.add(obj);
		}
		return list;
	}
}
