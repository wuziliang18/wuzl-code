package com.family.tools.code.create;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.family.tools.code.create.supports.AbstractCodeOperator;
import com.family.tools.code.create.supports.ParamBean;
import com.family.tools.db.operator.bean.DbConfBean;
import com.family.tools.db.operator.supports.DBUtil;
import com.family.tools.db.operator.supports.DataSourceContextHolder;

public class BroadcastMainBatchTableNameUserSQL {
	public static void main(String[] args) throws Exception {
		DbConfBean bean=new DbConfBean();
		bean.setDriverClassName("com.mysql.jdbc.Driver");
		bean.setUrl("jdbc:mysql://dbserver:3306/broadcast?useUnicode=true&amp;characterEncoding=utf-8");
		bean.setUserName("kangseed");
		bean.setPassword("JuiIo90PoiUiejOiu38Hu");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		System.out.println(">>>>>load success");
		DataSourceContextHolder.setDataSourceContextHolder(bean);//指定数据源
		Map<String,String> PATH_MAP=new HashMap<String,String>();
		PATH_MAP.put("BEAN_PROJECT_PATH", "/home/wuzl/dayimawork/live/live-api");
		PATH_MAP.put("BEAN_PACKAGE_PATH", "com.yoloho.broadcast.api.model");
		PATH_MAP.put("DAO_PACKAGE_PATH", "com.yoloho.broadcast.provider.dao");
		PATH_MAP.put("DAO_IMPL_PACKAGE_PATH", "com.yoloho.broadcast.provider.dao.impl");
		PATH_MAP.put("DAO_PROJECT_PATH", "/home/wuzl/dayimawork/live/live-provider");
		PATH_MAP.put("DAO_SPRING_RESOURCES_PATH", "/");
		PATH_MAP.put("DAO_SPRING_FILE_NAME", "app-dao.xml");
		PATH_MAP.put("MYBATIS_PACKAGE_PATH", "mybatis");
		PATH_MAP.put("MYBATIS_CONF_FILE_NAME", "mybatis-config.xml");
		
		PATH_MAP.put("SERVICE_INTERFACES_PROJECT_PATH", "/home/wuzl/dayimawork/live/live-api");
		PATH_MAP.put("SERVICE_IMPL_PROJECT_PATH", "/home/wuzl/dayimawork/live/live-provider");
		PATH_MAP.put("SERVICE_PACKAGE_PATH", "com.yoloho.broadcast.api.interfaces");
		PATH_MAP.put("SERVICE_IMPL_PACKAGE_PATH", "com.yoloho.broadcast.provider.service.impl");
		PATH_MAP.put("SERVICE_SPRING_FILE_NAME", "app-service.xml");
		PATH_MAP.put("SERVICE_SPRING_RESOURCES_PATH", "/");
		PATH_MAP.put("DUBBO_PROVIDER_FILE_NAME", "app-dubbo-provider.xml");
		ParamBean paramBean=new ParamBean();
		AbstractCodeOperator mybatisDaoImplCodeCreate=context.getBean("mybatisDaoImplCodeCreate",AbstractCodeOperator.class);
		AbstractCodeOperator serviceImplCodeCreate=context.getBean("serviceImplCodeCreate",AbstractCodeOperator.class);
		AbstractCodeOperator dubboSpringXmlUpdate=context.getBean("dubboSpringXmlUpdate",AbstractCodeOperator.class);
		String selectSql="select table_name from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA ='broadcast' and table_name like 'direc_broadcast_hidden_user'";
		List<Map<String,String>> tables=DBUtil.selectList(selectSql);
    	for(Map<String,String> table:tables){
    		String tableName=table.get("table_name");
//    		if(tableName.equals("topic")){//跳过一些表
//    			continue;
//    		}
    		paramBean=new ParamBean();
    		paramBean.setPathMap(PATH_MAP);
    		paramBean.setTableName(tableName);
    		mybatisDaoImplCodeCreate.operatorCodeFile(paramBean);
    		serviceImplCodeCreate.operatorCodeFile(paramBean);
    		dubboSpringXmlUpdate.operatorCodeFile(paramBean);
    	}
	}
}
