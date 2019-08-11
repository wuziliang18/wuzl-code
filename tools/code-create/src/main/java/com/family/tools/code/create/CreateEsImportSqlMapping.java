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

public class CreateEsImportSqlMapping {
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
		String searchSql = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA ='dayima' and table_name like 'user_extra'";
		List<Map<String, String>> tables = DBUtil.selectList(searchSql);
		DBOperateHelper dBOperateHelper = context
				.getBean(DBOperateHelper.class);
		for (Map<String, String> table : tables) {
			String tableName = table.get("table_name");
			String id = "uid";
			List<ColumnBean> columns = dBOperateHelper
					.selectDBTableColumnList(tableName);
			String prefix = ConfigUtil.changeUnderLineToHump(tableName);
			StringBuilder selectSql = new StringBuilder("select ");
			for (int i = 0; i < columns.size(); i++) {// 解析列
				ColumnBean columnBean = columns.get(i);
				String columnName = columnBean.getColumn_name();
				String beanColumnName = prefix
						+ ConfigUtil.changeUnderLineToHumpAndFontUp(columnName);
				selectSql.append(tableName).append(".").append(columnName)
						.append(" as ").append(beanColumnName);
				// if (i != columns.size() - 1) {
				// selectSql.append(",");
				// }
				selectSql.append(",");
			}
			selectSql.append(id).append(" as _id");
			selectSql.append(" from ").append(tableName);
			System.out.println(String.format("======生成【%s】sql==========",
					tableName));
			System.out.println(selectSql);
		}
	}
}
