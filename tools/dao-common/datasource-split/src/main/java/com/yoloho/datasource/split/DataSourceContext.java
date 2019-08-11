package com.yoloho.datasource.split;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

import com.yoloho.datasource.split.DebugContext.DebugStatus;
import com.yoloho.datasource.split.annotation.SplitTable;
import com.yoloho.datasource.split.annotation.SplitTableAnnotationParser;
import com.yoloho.datasource.split.annotation.SplitTableInfo;
import com.yoloho.datasource.split.utils.Assert;
import com.yoloho.datasource.split.utils.DataSourceUtil;
import com.yoloho.datasource.split.utils.SplitFieldUtil;
import com.yoloho.datasource.split.vo.BaseDataSourceConfBean;
import com.yoloho.datasource.split.vo.DataSourceConfBean;

/**
 * 数据源管理
 * 后期数据源选择可以实现接口 兼容读写分离等策略
 * @author wuzl
 * 
 */
public class DataSourceContext {
	private static final ThreadLocal<DataSource> dataSourceContextHolder = new ThreadLocal<DataSource>();// 保存当前线程动态的数据源
	private static final Map<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap<String, DataSource>();// 保存动态的数据源集合
	private static BaseDataSourceConfBean BASE_CONF;
	private static final Log log = LogFactory.getLog(DataSourceContext.class);
	static {
		// 此处可以动态加载数据源 可以不在依赖SplitDatasourceInit
	}

	/**
	 * 配置数据库的基本配置
	 * 
	 * @param bean
	 */
	protected static final void setBaseConf(BaseDataSourceConfBean bean) {
		if (bean != null) {
			BASE_CONF = bean;
		}
	}

	/**
	 * 添加数据源
	 * 
	 * @param rows
	 */
	public static final void addDataSource(List<DataSourceConfBean> rows) {
		Assert.notNull(rows, "添加的数据源不可以为空");
		for (DataSourceConfBean bean : rows) {
			addDataSource(bean);
		}
	}

	/**
	 * 添加数据源
	 * 
	 * @param bean
	 */
	public static final void addDataSource(DataSourceConfBean bean) {
		Assert.notNull(bean, "添加的数据源不可以为空");
		String dataSourceName = bean.getDataSourceName();
		Assert.notEmpty(dataSourceName, "配置的数据源名称不可以为空");
		if (DATA_SOURCE_MAP.get(dataSourceName) != null) {
			throw new RuntimeException(String.format("数据源【%s】已经存在",
					dataSourceName));
		}
		try {
			synchronized (DATA_SOURCE_MAP) {
				if (DATA_SOURCE_MAP.get(dataSourceName) == null) {
					addDataSource(dataSourceName,
							DataSourceUtil.createDataSource(bean, BASE_CONF));
					String url = bean.getUrl();
					log.info(String.format("成功添加【%s】【%s】数据源", dataSourceName,
							url.substring(0, url.indexOf("?"))));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("添加【%s】数据源失败：%s",
					dataSourceName, e.getMessage()));
		}
	}

	/**
	 * 关闭所有数据源
	 */
	public static void closeAll() {
		for (Map.Entry<String, DataSource> entry : DATA_SOURCE_MAP.entrySet()) {
			close(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 关闭数据源
	 * 
	 * @param dataSourceName
	 * @param dataSource
	 */
	public static void close(String dataSourceName) {
		DataSource dataSource = DATA_SOURCE_MAP.get(dataSourceName);
		if (dataSource != null) {
			close(dataSourceName, dataSource);
		}
	}

	/**
	 * 关闭数据源
	 * 
	 * @param dataSourceName
	 * @param dataSource
	 */
	public static void close(String dataSourceName, DataSource dataSource) {
		try {
			DataSourceUtil.closeDataSource(dataSource);
			DATA_SOURCE_MAP.remove(dataSourceName);
			log.info(String.format("成功关闭数据源【%s】", dataSourceName));
		} catch (Exception e) {
			log.error(String.format("关闭数据源【%s】失败:%s", dataSourceName,
					e.getMessage()));
		}
	}

	private static final void addDataSource(String dataSourceName,
			DataSource dataSource) {
		Assert.notNull(dataSource, "添加的数据源不可以为空");
		DATA_SOURCE_MAP.put(dataSourceName, dataSource);
	}

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	public final static DataSource getDataSourceContextHolder() {
		return dataSourceContextHolder.get();
	}

	/**
	 * 选择合适的数据源
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object determineDataSource(ProceedingJoinPoint pjp) throws Throwable {
		DataSource oldDataSource = dataSourceContextHolder.get();// 保留老的数据源信息
		try {
			// 获取拦截数据
			Object target = pjp.getTarget();
			// 判断是否需要切换数据源
			SplitTable annotation = target.getClass().getAnnotation(
					SplitTable.class);
			if (annotation != null) {
				// 指定动态的数据源
				String methodName = pjp.getSignature().getName();
				// 调用方法的参数
				Object[] args = pjp.getArgs();
				SplitTableInfo splitTableInfo = SplitTableAnnotationParser
						.parseSplitTableAnnotation(annotation);
				// debug拦截
				if (splitTableInfo.isDebug()) {
					DebugStatus debugStatus = DebugContext.getDebugStatus(
							splitTableInfo, methodName);
					if (debugStatus == DebugStatus.searchInDebug
							|| debugStatus == DebugStatus.searchNotInDebug) {
						dataSourceContextHolder.remove();// 查询 使用默认的数据源
						return pjp.proceed();
					}
					if (debugStatus == DebugStatus.cudInDebug) {
						dataSourceContextHolder.remove();// debug双写使用默认的数据源
						return pjp.proceed();
					}
				}
				this.chooseDataSource(splitTableInfo, target, methodName, args);
			}
			return pjp.proceed();
		} finally {
			dataSourceContextHolder.set(oldDataSource);
		}

	}

	private void chooseDataSource(SplitTableInfo splitTableInfo, Object target,
			String methodName, Object[] args) {
		String tableName = splitTableInfo.getTableName();
		String splitField = splitTableInfo.getSplitField();
		boolean forceSplit = splitTableInfo.isForceSplit();

		if (args.length > 0) {
			// 取分表的字段值
			Long filedValue = SplitFieldUtil.getSplitFieldValue(args,
					splitField);
			if (filedValue != null) {
				String dataSourceName = DataSourceStrategyContext
						.getDataSourceName(tableName, splitField, filedValue);
				if (dataSourceName != null && !"".equals(dataSourceName)) {
					DataSource dataSource = DATA_SOURCE_MAP.get(dataSourceName);
					Assert.notNull(dataSource, String.format(
							"没有指定名称【%s】的数据源，表名：【%s】切分字段：【%s】", dataSourceName,
							tableName, splitField));
					dataSourceContextHolder.set(dataSource);
				}
			} else if (forceSplit) {
				// TODO 后期可以考虑用方法级别注解
				throw new RuntimeException(String.format(
						"类【%s】的方法【%s】分表字段值不存在，如果不需要请在注解中加入forceSplit:false",
						target.getClass().getName(), methodName));
			}

		} else if (forceSplit) {
			throw new RuntimeException(String.format(
					"类【%s】的方法【%s】没有指定的切分字段，如果不需要请在注解中加入forceSplit:false",
					target.getClass().getName(), methodName));
		}
	}
}
