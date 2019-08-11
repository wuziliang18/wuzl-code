package com.yoloho.cache.memcache.support;

import java.util.List;


public class ClassCacheInfo {
	private String cacheName;
	private String[] primaryKeys;
	private String uniqueKey;
	private String[] groupKeys;
	private String[] groupCacheNames;
	private boolean removeSearchDb;
	private List<String> supportMethods;
	private boolean insertClearGroupCache;
	public boolean isInsertClearGroupCache() {
		return insertClearGroupCache;
	}
	public void setInsertClearGroupCache(boolean insertClearGroupCache) {
		this.insertClearGroupCache = insertClearGroupCache;
	}
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	public String[] getPrimaryKeys() {
		return primaryKeys;
	}
	public void setPrimaryKeys(String[] primaryKeys) {
		this.primaryKeys = primaryKeys;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String[] getGroupKeys() {
		return groupKeys;
	}
	public void setGroupKeys(String[] groupKeys) {
		this.groupKeys = groupKeys;
	}
	public String[] getGroupCacheNames() {
		return groupCacheNames;
	}
	public void setGroupCacheNames(String[] groupCacheNames) {
		this.groupCacheNames = groupCacheNames;
	}
	public boolean isRemoveSearchDb() {
		return removeSearchDb;
	}
	public void setRemoveSearchDb(boolean removeSearchDb) {
		this.removeSearchDb = removeSearchDb;
	}
	public List<String> getSupportMethods() {
		return supportMethods;
	}
	public void setSupportMethods(List<String> supportMethods) {
		this.supportMethods = supportMethods;
	}
	
}
