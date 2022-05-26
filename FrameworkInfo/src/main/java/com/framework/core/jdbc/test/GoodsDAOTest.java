package com.framework.core.jdbc.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.framework.core.jdbc.dao.IGoodsDao;
import com.framework.core.jdbc.dao.impl.GoodsDaoImpl;
import com.framework.core.jdbc.domain.Goods;
import com.framework.core.jdbc.query.GoodsQueryObject;
import com.framework.core.page.PageResult;

@SuppressWarnings("all")
public class GoodsDAOTest {

	private IGoodsDao dao = new GoodsDaoImpl();

	@Test
	public void testSave() {
		for (int i = 0; i < 10; i++) {
			Goods good = new Goods(null, "华为手机" + i, "华为", new BigDecimal(1000));
			dao.save(good);
		}
	}

	@Test
	public void testDelete() {
		dao.delete(1L);
	}

	@Test
	public void testUpdate() {
		Goods good = new Goods(2L, "魅族", "魅蓝", new BigDecimal(800));
		dao.update(good);

	}

	@Test
	public void testGet() {
		Goods goods = dao.get(2L);
		System.out.println(goods);
	}

	@Test
	public void testGetAll() {
		List<Goods> list = dao.getAll();
		for (Goods goods : list) {
			System.out.println(goods);
		}
	}

	@Test
	public void testQuery() {
		GoodsQueryObject go = new GoodsQueryObject();
		PageResult query = dao.query(go);
		System.out.println(query);
	}

}
