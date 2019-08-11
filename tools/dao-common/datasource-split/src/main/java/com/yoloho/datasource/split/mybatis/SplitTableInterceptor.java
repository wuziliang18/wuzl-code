package com.yoloho.datasource.split.mybatis;

import java.sql.Connection;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

import com.yoloho.datasource.split.DebugContext;
import com.yoloho.datasource.split.TableStrategyContext;
import com.yoloho.datasource.split.DebugContext.DebugStatus;
import com.yoloho.datasource.split.annotation.SplitTableAnnotationParser;
import com.yoloho.datasource.split.annotation.SplitTableInfo;
import com.yoloho.datasource.split.utils.SplitFieldUtil;
import com.yoloho.mybatis.common.PageInterceptor;

/**
 * 对分表的sql拦截处理
 * 
 * @author wuzl
 * 
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class SplitTableInterceptor extends PageInterceptor {
	public static final String TABLE_SPLIT_SUFFIX = "{table_split_suffix}";

	@Override
	protected String sqlProcess(BoundSql boundSql,
			MappedStatement mappedStatement) {
		String sql = super.sqlProcess(boundSql, mappedStatement);
		/* 1.取表名 */
		String resource = mappedStatement.getResource();
		String tableName = resource.substring(resource.indexOf("/") + 1,
				resource.lastIndexOf("."));

		/* 2.判断是否分表 */
		SplitTableInfo splitTableInfo = SplitTableAnnotationParser
				.getByTableName(tableName);
		if (splitTableInfo == null) {
			return sql;
		}
		/* 3.获取请求参数 */
		Object requestParam = boundSql.getParameterObject();
		if (requestParam == null) {
			throw new RuntimeException(String.format("表【%s】是被指定为分表的，请求参数不可以为空",
					tableName));
		}
		/* 4.debug */
		if (splitTableInfo.isDebug()) {
			String id = mappedStatement.getId();
			String methodName = id.substring(id.lastIndexOf(".") + 1,
					id.length());
			DebugStatus debugStatus = DebugContext.getDebugStatus(
					splitTableInfo, methodName);
			if (debugStatus == DebugStatus.searchInDebug
					|| debugStatus == DebugStatus.searchNotInDebug) {// 查询直接返回主库的
				return sql.replace(TABLE_SPLIT_SUFFIX,
						splitTableInfo.getDefaultTableName());
			}
			if (debugStatus == DebugStatus.cudNotInDebug) {// 没有去额外运行这个sql
				debugStatus.processBackUp(tableName, methodName, requestParam);// 双写
			}
			if (debugStatus == DebugStatus.cudInDebug) {// 正在运行这个sql是主表的
				return sql.replace(TABLE_SPLIT_SUFFIX,
						splitTableInfo.getDefaultTableName());
			}
		}
		/* 5.获取真正的请求表明 */
		String field = splitTableInfo.getSplitField();
		Long splitFieldValue = SplitFieldUtil.getSplitFieldValueFromMybatis(
				new Object[] { requestParam }, field);
		boolean forceSplit = splitTableInfo.isForceSplit();
		if (splitFieldValue != null) {
			sql = sql.replace(TABLE_SPLIT_SUFFIX, String
					.valueOf(TableStrategyContext.getRealTableSuffix(tableName,
							field, splitFieldValue)));
		} else if (forceSplit) {
			// TODO 后期可以考虑用方法级别注解
			throw new RuntimeException(String.format(
					"表【%s】分表字段值不存在，如果不需要请在注解中加入forceSplit:false", tableName));
		}
		return sql;
	}
}