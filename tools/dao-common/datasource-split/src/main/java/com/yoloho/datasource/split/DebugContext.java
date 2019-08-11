package com.yoloho.datasource.split;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yoloho.datasource.split.annotation.SplitTableInfo;
import com.yoloho.datasource.split.utils.Assert;
import com.yoloho.datasource.split.utils.ConfigUtil;
import com.yoloho.service.api.GenericService;
import com.yoloho.spring.util.SpringUtils;

/**
 * 调试模式
 * 
 * @author wuzl
 * 
 */
public class DebugContext {
	private static final List<Pattern> SEARCH_METHOD_PATTERN_LIST = new ArrayList<Pattern>();// 查询方法列表
	private static final List<String> CUD_METHOD_LIST = new ArrayList<String>();// 增改删方法列表
	private static final ThreadLocal<Boolean> IN_DEBUG_HOLDER = new ThreadLocal<Boolean>();// 是否在debug模式下
	private static final Map<String, String> TABLE_SERVICE_BEAN_MAP = new HashMap<String, String>();// 配置
																									// table呢bean名称对应关系
	private static final Log log = LogFactory.getLog(DebugContext.class);
	static {
		// 加载查询方法正则列表
		SEARCH_METHOD_PATTERN_LIST.add(Pattern.compile("get.*"));
		SEARCH_METHOD_PATTERN_LIST.add(Pattern.compile("find.*"));
		SEARCH_METHOD_PATTERN_LIST.add(Pattern.compile("search.*"));
		SEARCH_METHOD_PATTERN_LIST.add(Pattern.compile("count.*"));
		// 加载增改删方法列表 注意此处只处理GenericService中支持的
		CUD_METHOD_LIST.add("insert");
		CUD_METHOD_LIST.add("update");
		CUD_METHOD_LIST.add("updateByBean");// mybatis单独配置的
		CUD_METHOD_LIST.add("remove");
		CUD_METHOD_LIST.add("delete");
		// table呢bean名称对应关系
	}

	/**
	 * 当前状态枚举
	 * 
	 * @author wuzl
	 * 
	 */
	public enum DebugStatus {
		closeDebug, // 没有开启debug
		searchNotInDebug, // 查询debug,但没有在debug模式下运行
		searchInDebug, // 查询debug,并且在debug模式下运行
		cudNotInDebug, // 增改删debug,但没有在debug模式下运行
		cudInDebug; // 增改删debug,并且在debug模式下运行
		public void processBackUp(String tableName, String method, Object param) {// 处理双写
			if (this == cudNotInDebug) {
				log.info(String.format("debug下模式开始双写，表是【%s】，方法是【%s】",
						tableName, method));
				if (!isCudMethod(method)) {
					throw new RuntimeException(String.format("暂不支持【%s】方法的双写",
							method));
				}
				try {
					openDebug();
					String beanName = getServiceNameFromTable(tableName);
					@SuppressWarnings("unchecked")
					GenericService<Object, Serializable> service = (GenericService<Object, Serializable>) SpringUtils
							.getBean(beanName);
					Assert.notNull(service, String.format(
							"没有配置SpringUtils，获取bean【%s】不存在", beanName));
					if ("insert".equals(method)) {
						service.insert(param);
					} else if ("remove".equals(method)
							|| "delete".equals(method)) {
						service.remove((Serializable) param);
					} else if ("update".equals(method)
							|| "updateByBean".equals(method)) {
						service.update(param);
					}
				} finally {
					// 必须关闭
					closeDebug();
				}
			}
		}
	}

	/**
	 * 根据tablename获取servicename
	 * 
	 * @param tableName
	 * @return
	 */
	static String getServiceNameFromTable(String tableName) {
		String beanName = TABLE_SERVICE_BEAN_MAP.get(tableName);
		if (beanName != null) {
			return beanName;
		}
		beanName = ConfigUtil.changeUnderLineToHump(tableName.trim())
				+ "Service";
		TABLE_SERVICE_BEAN_MAP.put(tableName, beanName);
		return beanName;
	}

	/**
	 * 新增表名与service名字的对应
	 * 
	 * @param tableNameServiceMap
	 */
	public static void putTableNameForService(Map<String, String> tableNameServiceMap) {
		if (tableNameServiceMap != null) {
			TABLE_SERVICE_BEAN_MAP.putAll(tableNameServiceMap);
		}
	}

	/**
	 * 判断是否是查询方法
	 * 
	 * @param method
	 * @return
	 */
	public static boolean isSearchMethod(String method) {
		for (Pattern pattern : SEARCH_METHOD_PATTERN_LIST) {
			if (pattern.matcher(method).matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否是cud方法
	 * 
	 * @param method
	 * @return
	 */
	public static boolean isCudMethod(String method) {
		return CUD_METHOD_LIST.contains(method);
	}

	/**
	 * 开启debug模式
	 */
	private static void openDebug() {
		IN_DEBUG_HOLDER.set(true);
	}

	/**
	 * 关闭debug模式
	 */
	private static void closeDebug() {
		IN_DEBUG_HOLDER.remove();
	}

	static boolean checkDebugStatus() {
		Boolean status = IN_DEBUG_HOLDER.get();
		if (status == null) {
			return false;
		}
		return status;
	}

	/**
	 * 获取当前debug的状态
	 * 
	 * @param splitTableInfo
	 * @param methodName
	 * @return
	 */
	public static DebugStatus getDebugStatus(SplitTableInfo splitTableInfo,
			String methodName) {
		Assert.notNull(splitTableInfo, "分表信息不可以为空");
		if (splitTableInfo.isDebug()) {
			if (isSearchMethod(methodName)) {// 查询方法
				if (checkDebugStatus()) {
					return DebugStatus.searchInDebug;
				} else {
					return DebugStatus.searchNotInDebug;
				}
			} else if (isCudMethod(methodName)) {// 增删改
				if (checkDebugStatus()) {
					return DebugStatus.cudInDebug;
				} else {
					return DebugStatus.cudNotInDebug;
				}
			}
		}
		return DebugStatus.closeDebug;
	}
}
