package com.family.tools.code.create;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.family.tools.db.operator.bean.ColumnBean;
import com.family.tools.db.operator.bean.DbConfBean;
import com.family.tools.db.operator.dao.DBOperateHelper;
import com.family.tools.db.operator.supports.DBUtil;
import com.family.tools.db.operator.supports.DataSourceContextHolder;
import com.family.util.ConfigUtil;

public class CreateEsMapping {
	public static void main(String[] args) throws Exception {
		DbConfBean bean = new DbConfBean();
		bean.setDriverClassName("com.mysql.jdbc.Driver");
		bean.setUrl("jdbc:mysql://dbserver:3306/dayima?useUnicode=true&amp;characterEncoding=utf-8");
		bean.setUserName("kangseed");
		bean.setPassword("JuiIo90PoiUiejOiu38Hu");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		System.out.println(">>>>>load success");
		DataSourceContextHolder.setDataSourceContextHolder(bean);// 指定数据源
		String selectSql = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA ='dayima' and table_name like 'user_extra'";
		List<Map<String, String>> tables = DBUtil.selectList(selectSql);
		DBOperateHelper dBOperateHelper = context
				.getBean(DBOperateHelper.class);
		List<String> textFields = Arrays.asList("title", "content","descs","nick");
		String id="uid";
		for (Map<String, String> table : tables) {
			String tableName = table.get("table_name");
			List<ColumnBean> columns = dBOperateHelper
					.selectDBTableColumnList(tableName);
			String prefix = ConfigUtil.changeUnderLineToHump(tableName);
			for (ColumnBean column : columns) {
				System.out.println("fieldMap = new HashMap<String, Object>();");
				String columnName = column.getColumn_name();
				String beanColumnName = prefix
						+ ConfigUtil.changeUnderLineToHumpAndFontUp(columnName);
				if (textFields.contains(columnName)) {
					System.out.println("fieldMap.put(\"type\", \"string\");");
					System.out.println("fieldMap.put(\"analyzer\", \"ik\");");
				} else {
					String mappingType = "string";
					switch (column.getJavaType()) {
					case "Date":
						mappingType = "data";
						break;
					case "Long":
						mappingType = "long";
						break;
					case "Integer":
						mappingType = "integer";
					case "Double":
						mappingType = "long";
					default:
						break;
					}
					System.out.println("fieldMap.put(\"type\", \""
							+ mappingType + "\");");
					// 索引这个字段，使之可以被搜索，但是索引内容和指定值一样。不分析此字段。
					System.out
							.println("fieldMap.put(\"index\", \"not_analyzed\");");
				}
				System.out.println("properties.put(\"" + beanColumnName
						+ "\", fieldMap);");
			}
//			System.out.println("//设置主键");
//			System.out.println("Map<String, Object> idMap = new HashMap<String, Object>();");
//			System.out.println("idMap.put(\"path\", \""+prefix
//					+ ConfigUtil.changeUnderLineToHumpAndFontUp(id)+"\");");
//			System.out.println("mapping.put(\"_id\", idMap);");
		}
	}
}
