package com.jpa.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年7月3日 上午1:26:04
 */
public class MapParamsBuilder {

	private Map<String, Object> map = new LinkedHashMap<>();
	private Map<String, Object> orTemp;
	private Map<String, Object> andTemp;

	public static MapParamsBuilder getInstance() {
		return new MapParamsBuilder();
	}

	public MapParamsBuilder with(String key, Object value) {
		if (value == null) {
			return this;
		} else {
			this.getMap().put(key, value);
			return this;
		}
	}

	public MapParamsBuilder distinct() {
		this.getMap().put("DISTINCT", true);
		return this;
	}

	public MapParamsBuilder empty() {
		this.getMap().put("EMPTY", true);
		return this;
	}

	public MapParamsBuilder fetch(String field) {
		this.getMap().put("FETCH", field);
		return this;
	}

	public MapParamsBuilder ignoreTenantId() {
		this.getMap().put("IGNORETENANTID", true);
		return this;
	}

	public MapParamsBuilder ignoreDataPermissions(String fields) {
		this.getMap().put("IGNOREDATAPERMISSIONS", fields);
		return this;
	}

	public Map<String, Object> build() {
		return this.map;
	}

	public MapParamsBuilder eq(String field, Object value) {
		this.getMap().put("EQ_" + field, value);
		return this;
	}

	public MapParamsBuilder ne(String field, Object value) {
		this.getMap().put("NE_" + field, value);
		return this;
	}

	public MapParamsBuilder lt(String field, Object value) {
		this.getMap().put("LT_" + field, value);
		return this;
	}

	public MapParamsBuilder le(String field, Object value) {
		this.getMap().put("LE_" + field, value);
		return this;
	}

	public MapParamsBuilder gt(String field, Object value) {
		this.getMap().put("GT_" + field, value);
		return this;
	}

	public MapParamsBuilder ge(String field, Object value) {
		this.getMap().put("GE_" + field, value);
		return this;
	}

	public MapParamsBuilder like(String field, Object value) {
		this.getMap().put("LIKE_" + field, value);
		return this;
	}

	public MapParamsBuilder notLike(String field, Object value) {
		this.getMap().put("NOTLIKE_" + field, value);
		return this;
	}

	public MapParamsBuilder in(String field, Object value) {
		this.getMap().put("IN_" + field, value);
		return this;
	}

	public MapParamsBuilder notIn(String field, Object value) {
		this.getMap().put("NOTIN_" + field, value);
		return this;
	}

	public MapParamsBuilder isNull(String field) {
		this.getMap().put("IS_" + field, "NULL");
		return this;
	}

	public MapParamsBuilder isNotNull(String field) {
		this.getMap().put("IS_" + field, "NOTNULL");
		return this;
	}

	public MapParamsBuilder isBlank(String field) {
		this.getMap().put("IS_" + field, "BLANK");
		return this;
	}

	public MapParamsBuilder isNotBlank(String field) {
		this.getMap().put("IS_" + field, "NOTBLANK");
		return this;
	}

	public MapParamsBuilder isTrue(String field) {
		this.getMap().put("IS_" + field, "TRUE");
		return this;
	}

	public MapParamsBuilder isFlase(String field) {
		this.getMap().put("IS_" + field, "FALSE");
		return this;
	}

	public MapParamsBuilder size(String field, int size) {
		this.getMap().put("SIZE_" + field, size);
		return this;
	}

	public MapParamsBuilder notSize(String field, int size) {
		this.getMap().put("NOTSIZE_" + field, size);
		return this;
	}

	public MapParamsBuilder exists(String field, JpaSubquery subquery) {
		this.getMap().put("EXISTS_" + field, subquery);
		return this;
	}

	public MapParamsBuilder notExists(String field, JpaSubquery subquery) {
		this.getMap().put("NOTEXISTS_" + field, subquery);
		return this;
	}

	public MapParamsBuilder startOr() {
		if (this.orTemp != null) {
			throw new IllegalStateException("上一次AND或OR操作未结束，不支持嵌套。");
		} else {
			this.orTemp = new LinkedHashMap();
			return this;
		}
	}

	public MapParamsBuilder startAnd() {
		if (this.orTemp == null && this.andTemp == null) {
			this.andTemp = new LinkedHashMap();
			return this;
		} else {
			throw new IllegalStateException("上一次AND或OR操作未结束，不支持嵌套。");
		}
	}

	public MapParamsBuilder endOr() {
		String finalKey = StringUtils.join(new ArrayList(this.orTemp.keySet()), " || ");
		this.map.put(finalKey, new ArrayList(this.orTemp.values()));
		this.orTemp.clear();
		this.orTemp = null;
		return this;
	}

	public MapParamsBuilder endAnd() {
		String finalKey = StringUtils.join(new ArrayList(this.andTemp.keySet()), " && ");
		this.map.put(finalKey, new ArrayList(this.andTemp.values()));
		this.andTemp.clear();
		this.andTemp = null;
		return this;
	}

	private Map<String, Object> getMap() {
		if (this.orTemp != null) {
			return this.orTemp;
		} else {
			return this.andTemp != null ? this.andTemp : this.map;
		}
	}
}
