package com.yoloho.mybatis.common.filter;

import java.util.Map;

import com.yoloho.dao.api.filter.DynamicSQLFilterImpl;
import com.yoloho.dao.api.filter.QueryFilter;
import com.yoloho.dao.api.filter.SimpleFilterImpl;

public class MybatisFilterParse {
	public static Map<String, Object> getParameters(QueryFilter filter) {
		if(filter==null){
			return null;
		}
		if(filter instanceof SimpleFilterImpl){
			return SimpleFilterImplParse.getParameters((SimpleFilterImpl)filter);
		}else {
			return DynamicSQLFilterParse.getParameters((DynamicSQLFilterImpl)filter);
		}
	}
}
