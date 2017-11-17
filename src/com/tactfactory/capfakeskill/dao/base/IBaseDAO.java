package com.tactfactory.capfakeskill.dao.base;

import java.util.List;

import com.tactfactory.capfakeskill.entities.base.BaseEntity;

public interface IBaseDAO {

	public BaseEntity insert(BaseEntity item);
	public void delete(BaseEntity item);
	public void update(BaseEntity item);
	public BaseEntity select(BaseEntity item);
	public List<BaseEntity> select();
}
