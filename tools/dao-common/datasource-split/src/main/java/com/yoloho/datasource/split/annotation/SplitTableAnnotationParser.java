package com.yoloho.datasource.split.annotation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.yoloho.datasource.split.utils.Assert;
import com.yoloho.spring.util.ScanPackageAnnotation;

/**
 * 分表注解解析
 * 
 * @author wuzl
 * 
 */
public class SplitTableAnnotationParser implements InitializingBean {
	private List<String> scanPackageName;// 扫描包
	private static final ConcurrentMap<String, SplitTableInfo> splitMap = new ConcurrentHashMap<String, SplitTableInfo>();// key是表名
	private static final Log log = LogFactory
			.getLog(SplitTableAnnotationParser.class);

	public void setScanPackageName(List<String> scanPackageName) {
		this.scanPackageName = scanPackageName;
	}

	/**
	 * 加载所有的注解信息
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		for (String packageName : scanPackageName) {
			Map<String, SplitTable> map = ScanPackageAnnotation
					.scanAnnotationForMap(packageName, SplitTable.class);
			for (Map.Entry<String, SplitTable> entry : map.entrySet()) {
				parseSplitTableAnnotation(entry.getValue());
			}
			log.info(String.format("扫描【%s】包下的分表注解成功", packageName));
		}

	}

	/**
	 * 根据表明获取分表信息
	 * 
	 * @param tableName
	 * @return
	 */
	public static SplitTableInfo getByTableName(String tableName) {
		return splitMap.get(tableName);
	}

	/**
	 * 解析表切分的注解
	 * 
	 * @param annotation
	 * @return
	 */
	public static SplitTableInfo parseSplitTableAnnotation(SplitTable annotation) {
		String tableName = annotation.value().trim();
		Assert.notEmpty(tableName, "表名称不可以为空");
		SplitTableInfo info = splitMap.get(tableName);
		if (info != null) {
			return info;
		}
		synchronized (SplitTableAnnotationParser.class) {
			info = splitMap.get(tableName);
			if (info == null) {
				String splitField = annotation.splitField().trim();
				Assert.notEmpty(splitField, "切分字段不可以为空");
				String defaultTableName = annotation.defaultTable();
				info = new SplitTableInfo();
				info.setSplitField(splitField);
				info.setTableName(tableName);
				info.setDebug(annotation.debug());
				info.setDefaultTableName(defaultTableName);
				info.setForceSplit(annotation.forceSplit());
				splitMap.put(tableName, info);
				log.info(String.format("切分【%s】表注解解析成功：%s", tableName,
						info.toString()));
			}
		}
		return info;
	}

}
