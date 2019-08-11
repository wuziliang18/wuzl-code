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

public class CreateEsBean {
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
		String packagePath="com.dayima.es.search.api.model";
		String filePath="/home/wuzl/dayimawork/es-search/search-api/src/main/java/com/dayima/es/search/api/model";
		for (Map<String, String> table : tables) {
			String tableName = table.get("table_name");
			String beanName = "Es"
					+ ConfigUtil.changeUnderLineToHumpAndFontUp(tableName);
			List<ColumnBean> columns = dBOperateHelper
					.selectDBTableColumnList(tableName);
			StringBuilder code = new StringBuilder();
			StringBuilder setGetMethod = new StringBuilder();// 保存getset方法体
			code.append("package " + packagePath + ";" + LINE_SEPARATOR
					+ LINE_SEPARATOR);
			code.append("import java.io.Serializable;" + LINE_SEPARATOR);
			code.append("public class " + beanName
					+ " implements Serializable {" + LINE_SEPARATOR);
			code.append("\tprivate static final long serialVersionUID = -6188544384149284540L;"
					+ LINE_SEPARATOR);
			String prefix = ConfigUtil.changeUnderLineToHump(tableName);
			String prefixFont=ConfigUtil.changeUnderLineToHumpAndFontUp(tableName);
			for (ColumnBean columnBean : columns) {
				String javaType = columnBean.getJavaType();
				String columnName = columnBean.getColumn_name();
				String columnNameHump =prefix
						+ ConfigUtil.changeUnderLineToHumpAndFontUp(columnName);
				String columnNameHumpFont = prefixFont
						+ ConfigUtil.changeUnderLineToHumpAndFontUp(columnName);
				code.append("\tprivate " + javaType + " " + columnNameHump
						+ ";" + LINE_SEPARATOR);
				setGetMethod.append("\tpublic " + javaType + " " + "get"
						+ columnNameHumpFont + "() {" + LINE_SEPARATOR);
				setGetMethod.append("\t\treturn " + columnNameHump + ";"
						+ LINE_SEPARATOR);
				setGetMethod.append("\t}" + LINE_SEPARATOR + LINE_SEPARATOR);
				setGetMethod.append("\tpublic void set" + columnNameHumpFont
						+ "(" + javaType + " " + columnNameHump + ") {"
						+ LINE_SEPARATOR);
				setGetMethod.append("\t\tthis." + columnNameHump + " = "
						+ columnNameHump + ";" + LINE_SEPARATOR);
				setGetMethod.append("\t}" + LINE_SEPARATOR + LINE_SEPARATOR);
			}
			code.append(LINE_SEPARATOR);
			code.append(setGetMethod);
			code.append("}" + LINE_SEPARATOR);
			String javaFilePath=filePath+"/"+beanName+".java";
			FileUtil.writeAndValidateFile(code.toString(), javaFilePath);
			System.out.println(String.format("生成%s的esbean,路径:%s", tableName,javaFilePath));
		}
	}
}
