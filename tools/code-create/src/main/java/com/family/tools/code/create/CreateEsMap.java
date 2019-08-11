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

public class CreateEsMap {
	public static String LINE_SEPARATOR = System.getProperty("line.separator");
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
		for (Map<String, String> table : tables) {
			String tableName = table.get("table_name");
			List<ColumnBean> columns = dBOperateHelper
					.selectDBTableColumnList(tableName);
			String prefix = ConfigUtil.changeUnderLineToHump(tableName);
			StringBuilder code = new StringBuilder();
			String objectName="esUser";
			String prefixFont = ConfigUtil
					.changeUnderLineToHumpAndFontUp(tableName);
			for (ColumnBean column : columns) {
				String javaType = column.getJavaType();
				String columnName = column.getColumn_name();
				String mapName="map";
				String columnNameHumpFont = prefixFont
						+ ConfigUtil.changeUnderLineToHumpAndFontUp(columnName);
				String columnNameHump = prefix
						+ ConfigUtil.changeUnderLineToHumpAndFontUp(columnName);
//				System.out.println("dto.put(\""+columnName+"\", \""+beanColumnName+"\");");
				code.append(
						"\t\tObject " + columnNameHump + "="+mapName+".get(\""
			
								+ columnNameHump + "\");").append(
						LINE_SEPARATOR);
				code.append("\t\tif (" + columnNameHump + " != null) {")
						.append(LINE_SEPARATOR);
				code.append("\t\t\t").append(objectName)
						.append(".set" + columnNameHumpFont).append("(");
				if (javaType.equals("Long")) {
					code.append("Long.parseLong(String.valueOf(")
							.append(columnNameHump).append("))");
				} else if (javaType.equals("Integer")) {
					code.append("Integer.parseInt(String.valueOf(")
							.append(columnNameHump).append("))");
				} else {
					code.append("String.valueOf(").append(columnNameHump)
							.append(")");
				}
				code.append(");");
				code.append(LINE_SEPARATOR);
				code.append("\t\t}");
				code.append(LINE_SEPARATOR);
				System.out.println("private "+javaType+" "+columnNameHump+";");
			}
			System.out.println(code);
//			System.out.println("//设置主键");
//			System.out.println("Map<String, Object> idMap = new HashMap<String, Object>();");
//			System.out.println("idMap.put(\"path\", \""+prefix
//					+ ConfigUtil.changeUnderLineToHumpAndFontUp(id)+"\");");
//			System.out.println("mapping.put(\"_id\", idMap);");
		}
	}
}
