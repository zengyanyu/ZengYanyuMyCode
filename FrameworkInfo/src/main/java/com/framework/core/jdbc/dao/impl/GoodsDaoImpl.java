package com.framework.core.jdbc.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import com.framework.core.jdbc.dao.IGoodsDao;
import com.framework.core.jdbc.domain.Goods;
import com.framework.core.jdbc.handler.IResultSetHandler;
import com.framework.core.jdbc.handler.impl.BeanHandler;
import com.framework.core.jdbc.handler.impl.BeanListHandler;
import com.framework.core.jdbc.query.GoodsQueryObject;
import com.framework.core.jdbc.util.JdbcTemplate;
import com.framework.core.page.PageResult;

public class GoodsDaoImpl implements IGoodsDao {

	@Override
	public void save(Goods good) {
		String sql = "INSERT INTO t_goods(name,brand,sal) VALUES(?,?,?)";
		JdbcTemplate.update(sql, good.getName(), good.getBrand(), good.getSal());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM t_goods WHERE id = ? ";
		JdbcTemplate.update(sql, id);
	}

	@Override
	public void update(Goods good) {
		String sql = "UPDATE t_goods SET name = ?,brand = ?, sal = ? WHERE id = ? ";
		JdbcTemplate.update(sql, good.getName(), good.getBrand(), good.getSal(), good.getId());
	}

	@Override
	public Goods get(Long id) {
		String sql = "SELECT * FROM t_goods WHERE id = ?";
		return JdbcTemplate.query1(sql, new BeanHandler<>(Goods.class), id);
	}

	@Override
	public List<Goods> getAll() {
		String sql = "SELECT * FROM t_goods";
		return JdbcTemplate.query1(sql, new BeanListHandler<>(Goods.class));
	}

	//高级查询 + 分页查询
	@Override
	public PageResult query(GoodsQueryObject go) {
		String sql = " SELECT * FROM t_goods " + go.getConditions() + " LIMIT ?,? ";
		List<Object> params = go.getParams();
		params.add((go.getCurrentPage() - 1) * go.getPageSize());//分页查询中的第一个参数
		params.add(go.getPageSize());//分页查询中的第二个参数;
		List<Goods> listData = JdbcTemplate.query(sql, new BeanListHandler<>(Goods.class), params.toArray());
		sql = "SELECT count(id) FROM t_goods " + go.getConditions();
		Long totalCountLong = JdbcTemplate.query(sql, new IResultSetHandler<Long>() {

			@Override
			public Long handle(ResultSet rs) throws Exception {
				rs.next();
				return rs.getLong(1);
			}
		}, go.getParams().toArray());
		int totalCount = totalCountLong.intValue();
		return new PageResult(listData, totalCount, go.getCurrentPage(), go.getPageSize());
	}

	@Override
	public Long getTotalCount() {
		String sql = "SELECT COUNT(id) FROM t_goods ";
		return JdbcTemplate.query(sql, new IResultSetHandler<Long>() {

			@Override
			public Long handle(ResultSet rs) throws Exception {
				rs.next();
				return rs.getLong(1);
			}
		});
	}

}
