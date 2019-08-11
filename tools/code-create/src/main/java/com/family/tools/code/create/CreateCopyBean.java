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

public class CreateCopyBean {
	public static String LINE_SEPARATOR = System.getProperty("line.separator");

	public static void main(String[] args) throws Exception {
		DbConfBean bean = new DbConfBean();
		bean.setDriverClassName("com.mysql.jdbc.Driver");
		bean.setUrl("jdbc:mysql://dbserver:3306/dayima_data?useUnicode=true&amp;characterEncoding=utf-8");
		bean.setUserName("kangseed");
		bean.setPassword("JuiIo90PoiUiejOiu38Hu");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		System.out.println(">>>>>load success");
		DataSourceContextHolder.setDataSourceContextHolder(bean);// 指定数据源
		String searchSql = "select table_name from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA ='dayima_data' and table_name like 'user_info'";
		List<Map<String, String>> tables = DBUtil.selectList(searchSql);
		DBOperateHelper dBOperateHelper = context
				.getBean(DBOperateHelper.class);
		String beanName="bean";
		String targetBeanName="msg";
		for (Map<String, String> table : tables) {
			String tableName = table.get("table_name");
			List<ColumnBean> columns = dBOperateHelper
					.selectDBTableColumnList(tableName);
			StringBuilder sb=new StringBuilder();
			for (ColumnBean columnBean : columns) {
				String columnName = columnBean.getColumn_name();
				sb.append(columnName).append(",");

			}
			System.out.println(sb);
		}
	}
}
