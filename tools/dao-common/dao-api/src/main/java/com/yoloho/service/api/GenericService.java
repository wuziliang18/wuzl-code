package com.yoloho.service.api;

import java.io.Serializable;
import java.util.List;

import com.yoloho.dao.api.filter.QueryFilter;


public abstract interface GenericService<T, PK extends Serializable> {
	public abstract int insert(T paramT);

	public abstract int update(T paramT);

	public abstract T get(PK paramPK);
	

	public abstract List<T> find(QueryFilter filter);
	
	
	public abstract Integer count(QueryFilter filter);


	public abstract void remove(PK paramPK);

}