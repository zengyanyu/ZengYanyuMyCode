package com.jpa.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultMapperBuilder {
	
	private Map<String, Object> map = new LinkedHashMap<>();

	public static ResultMapperBuilder getInstance() {
		return new ResultMapperBuilder();
	}

	public ResultMapperBuilder with(String key) {
		this.map.put(key, (Object) null);
		return this;
	}

	public ResultMapperBuilder with(String... keys) {
		if (keys != null && keys.length > 0) {
			String[] var2 = keys;
			int var3 = keys.length;

			for (int var4 = 0; var4 < var3; ++var4) {
				String key = var2[var4];
				this.map.put(key, (Object) null);
			}
		}
		return this;
	}

	public Map<String, Object> build() {
		return this.map;
	}
	
}
