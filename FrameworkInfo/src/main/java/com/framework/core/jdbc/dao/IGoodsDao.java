package com.framework.core.jdbc.dao;

import java.util.List;

import com.framework.core.jdbc.domain.Goods;
import com.framework.core.jdbc.query.GoodsQueryObject;
import com.framework.core.page.PageResult;

public interface IGoodsDao {

	void save(Goods good);

	void delete(Long id);

	void update(Goods good);

	Goods get(Long id);

	List<Goods> getAll();

	PageResult query(GoodsQueryObject go);

	Long getTotalCount();

}
