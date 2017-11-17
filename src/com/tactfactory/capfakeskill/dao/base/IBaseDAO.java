package com.tactfactory.capfakeskill.dao.base;

import java.util.List;

import com.tactfactory.capfakeskill.entities.base.BaseEntity;

public interface IBaseDAO <T extends BaseEntity> {

	public T insert(T item);
	public void delete(T item);
	public void update(T item);
	public T select(T item);
	public List<T> select();

	public String getCreateTable();
}
