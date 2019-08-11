package com.yoloho.dao.datasource;

/**
 * 利用ThreadLocal控制读写分离数据源
 * @author wuzl
 *
 */
public class ReadWriteDataSourceDecision {
	public enum DataSourceType {
		write, read;
	}
	//读写分离描述
	private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<DataSourceType>();

	public static void markWrite() {
		holder.set(DataSourceType.write);
	}

	public static void markRead() {
		holder.set(DataSourceType.read);
	}

	public static void reset() {
		holder.set(null);
	}

	public static boolean isChoiceNone() {
		return null == holder.get();
	}

	public static boolean isChoiceWrite() {
		return DataSourceType.write == holder.get();
	}

	public static boolean isChoiceRead() {
		return DataSourceType.read == holder.get();
	}
}
