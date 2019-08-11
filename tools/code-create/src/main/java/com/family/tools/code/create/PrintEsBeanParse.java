package com.family.tools.code.create;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.family.tools.db.operator.bean.ColumnBean;
import com.family.tools.db.operator.bean.DbConfBean;
import com.family.tools.db.operator.dao.DBOperateHelper;
import com.family.tools.db.operator.supports.DBUtil;
import com.family.tools.db.operator.supports.DataSourceContextHolder;
import com.family.util.ConfigUtil;
import com.family.util.FileUtil;

public class PrintEsBeanParse {
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
		String searchSql = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA ='dayima' and table_name like 'topic_category'";
		List<Map<String, String>> tables = DBUtil.selectList(searchSql);
		DBOperateHelper dBOperateHelper = context
				.getBean(DBOperateHelper.class);
		String packagePath = "com.dayima.es.search.api.parse";
		String filePath = "/home/wuzl/dayimawork/es-search/search-api/src/main/java/com/dayima/es/search/api/parse";
		String beanPackagePath = "com.dayima.es.search.api.model";
		for (Map<String, String> table : tables) {
			String tableName = table.get("table_name");
			String beanParseName = "Es"
					+ ConfigUtil.changeUnderLineToHumpAndFontUp(tableName)
					+ "Parse";
			String beanName = "Es"
					+ ConfigUtil.changeUnderLineToHumpAndFontUp(tableName);
			List<ColumnBean> columns = dBOperateHelper
					.selectDBTableColumnList(tableName);
			StringBuilder code = new StringBuilder();
			StringBuilder setGetMethod = new StringBuilder();// 保存getset方法体
			code.append("package " + packagePath + ";" + LINE_SEPARATOR
					+ LINE_SEPARATOR);
			code.append("import " + beanPackagePath + "." + beanName + ";"
					+ LINE_SEPARATOR);
			code.append("import java.util.Map;" + LINE_SEPARATOR);
			code.append("import org.wuzl.client.common.support.BeanParse;"
					+ LINE_SEPARATOR);
			code.append("public class " + beanParseName
					+ " implements BeanParse<" + beanName + ">{"
					+ LINE_SEPARATOR);
			code.append(LINE_SEPARATOR);
			code.append("\t@Override").append(LINE_SEPARATOR);
			;
			code.append("\tpublic " + beanName
					+ " getBeanFromMap(Map<String, Object> map) {"
					+ LINE_SEPARATOR);
			String objectName = "es"
					+ ConfigUtil.changeUnderLineToHumpAndFontUp(tableName);
			;
			code.append("\t\t").append(beanName).append(" ").append(objectName)
					.append(" = new ").append(beanName).append("();")
					.append(LINE_SEPARATOR);

			String prefix = ConfigUtil.changeUnderLineToHump(tableName);
			String prefixFont = ConfigUtil
					.changeUnderLineToHumpAndFontUp(tableName);
			for (ColumnBean columnBean : columns) {
				String javaType = columnBean.getJavaType();
				String columnName = columnBean.getColumn_name();
				String columnNameHump = prefix
						+ ConfigUtil.changeUnderLineToHumpAndFontUp(columnName);
				String columnNameHumpFont = prefixFont
						+ ConfigUtil.changeUnderLineToHumpAndFontUp(columnName);
				code.append(
						"\t\tObject " + columnNameHump + "=map.get(\""
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

			}
			code.append(LINE_SEPARATOR);
			code.append(setGetMethod);

			code.append("\t\treturn " + objectName + ";")
					.append(LINE_SEPARATOR);
			;
			code.append("\t}" + LINE_SEPARATOR);
			code.append("}" + LINE_SEPARATOR);
			String javaFilePath = filePath + "/" + beanParseName + ".java";
			FileUtil.writeAndValidateFile(code.toString(), javaFilePath);
			System.out.println(String.format("生成%s的esbean,路径:%s", tableName,
					javaFilePath));
		}
	}
}
