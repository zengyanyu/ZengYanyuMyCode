package com.framework.core.jdbc.query;

import com.framework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class EmployeeQueryObject extends JdbcQueryObject {

	private String username;

	private String password;

	private String keyword;//关键字查询

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月3日 上午10:58:15
	 */
	@Override
	protected void customzied() {
		//用户名不为空,按照用户名进行模糊查询
		if (StringUtils.hasLength(username)) {
			this.add("username LIKE ?", "%" + this.username + "%");
		}
		if (StringUtils.hasLength(password)) {
			this.add("password LIKE ?", "%" + this.password + "%");
		}
		if (StringUtils.hasLength(keyword)) {
			this.add(" (username LIKE ? or password LIKE ?) ", "%" + this.username + "%", "%" + this.password + "%");
		}
	}

}
