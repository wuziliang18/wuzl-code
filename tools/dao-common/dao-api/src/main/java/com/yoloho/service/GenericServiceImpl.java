package com.yoloho.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yoloho.dao.api.GenericDao;
import com.yoloho.dao.api.filter.QueryFilter;
import com.yoloho.service.api.GenericService;

public class GenericServiceImpl<T, PK extends Serializable> implements
		GenericService<T, PK> {
	public static final Log logger = LogFactory.getLog(GenericServiceImpl.class);
	protected GenericDao<T, PK> dao = null;

	public GenericServiceImpl() {

	}

	public GenericServiceImpl(GenericDao<T, PK> dao) {
		this.dao = dao;
	}

	public void setDao(GenericDao<T, PK> dao) {
		this.dao = dao;
	}

	public T get(PK id) {
		return this.dao.get(id);
	}

	public int insert(T bean) {
		return this.dao.insert(bean);
	}

	public int update(T bean) {
		return this.dao.update(bean);
	}

	public List<T> find(QueryFilter filter) {
		return this.dao.find(filter);
	}

	public Integer count(QueryFilter filter) {
		return this.dao.count(filter);
	}

	public void remove(PK id) {
		this.dao.remove(id);
	}

}