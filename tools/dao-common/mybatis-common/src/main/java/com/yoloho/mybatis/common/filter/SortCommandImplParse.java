package com.yoloho.mybatis.common.filter;


import com.yoloho.dao.api.filter.QueryFilter;
import com.yoloho.dao.api.filter.SortCommandImpl;

public class SortCommandImplParse {
	
	public static String getPartSql(SortCommandImpl sortCommandImpl) {
		return (new StringBuilder(" order by ").append(QueryFilter.propertyToFieldName(String.valueOf(sortCommandImpl.getSortName())))).append(" ").append(sortCommandImpl.getIsDesc() ?"desc":"asc").toString();
	}

}
