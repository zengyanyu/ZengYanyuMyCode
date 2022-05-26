package com.framework.core.jdbc.query;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 上午10:53:28
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class GoodsQueryObject extends JdbcQueryObject {
	/**
	 * 高进查询操作中,封装的是需要通过什么方式进行查询,按照自己需要的
	 * 需求进行封装对应的数据;
	 */
	//最小值,最大值;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	//关键字
	private String keywords;

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午10:48:57
	 */
	@Override
	protected void customzied() {
		//名称不等于空,按照名称进行模糊查询
		if (StringUtils.hasLength(keywords)) {
			this.add("  (name like ? or brand like  ? ) ", "%" + this.keywords + "%", "%" + this.keywords + "%");
		}
		if (minPrice != null) {
			this.add(" sal >= ? ", this.minPrice);
		}
		if (maxPrice != null) {
			this.add(" sal <= ? ", this.maxPrice);
		}
	}

}
