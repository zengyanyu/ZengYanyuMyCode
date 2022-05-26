package com.hibernate.config;

import org.hibernate.criterion.MatchMode;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:46:05
 * @see com.hibernate.config.LikeExpression.java
 */
public class LikeExpression extends AbstractPropertyExpression {
	/**
	* 操作匹配模式
	*/
	protected MatchMode matchMode;

	/**
	 * 比较值
	 */
	protected Object value;

	/**
	 * 转义符
	 */
	protected Character escapeChar;

	/**
	 * 是否需要转义
	 */
	protected boolean esc = true;

	/**
	 * 构造函数.
	 *
	 * @param entityName 实体名称
	 * @param propName 属性名称
	 * @param value 比较值
	 * @param matchMode like操作模式
	 */
	public LikeExpression(String entityName, String propName, Object value, MatchMode matchMode) {
		super(entityName, propName);
		this.matchMode = matchMode;
		this.value = value;
	}

	/**
	 * 构造函数.
	 *
	 * @param entityName 实体名称
	 * @param propName 属性名称
	 * @param value 比较值
	 * @param matchMode like操作模式
	 * @param esc   是否需要转义，默认以'^'对"%"和"_"进行转义
	 */
	public LikeExpression(String entityName, String propName, Object value, MatchMode matchMode, boolean esc) {
		super(entityName, propName);
		this.matchMode = matchMode;
		this.value = value;
		this.esc = esc;
	}

	/**
	 * 构造函数.
	 *
	 * @param entityName 实体名称
	 * @param propName 属性名称
	 * @param value 比较值
	 */
	public LikeExpression(String entityName, String propName, Object value) {
		this(entityName, propName, value, MatchMode.ANYWHERE);
	}

	/**
	 * 构造函数.
	 *
	 * @param propName 属性名称
	 * @param value 比较值
	 */
	public LikeExpression(String propName, Object value) {
		this(null, propName, value);
	}

	/**
	 * 构造函数.
	 *
	 * @param propName 属性名称
	 * @param value 比较值
	 * @param matchMode like操作模式
	 */
	public LikeExpression(String propName, Object value, MatchMode matchMode) {
		this(null, propName, value, matchMode);
	}

	/**
	 * 获取操作匹配模式.
	 *
	 * @return 操作匹配模式
	 */
	public MatchMode getMatchMode() {
		return matchMode;
	}

	/**
	 * 获取比较值.
	 *
	 * @return 比较值
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 获取转义符
	 * @return
	 */
	public Character getEscapeChar() {
		return escapeChar;
	}

	/**
	 * 设置转义符
	 * @param escapeChar
	 */
	public void setEscapeChar(Character escapeChar) {
		this.escapeChar = escapeChar;
	}

	public void setEsc(boolean esc) {
		this.esc = esc;
	}

	public boolean isEsc() {
		return esc;
	}
}
