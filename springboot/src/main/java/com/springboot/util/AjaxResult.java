package com.springboot.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxResult {

	//设置默认为true
	private boolean success = true;

	//提示信息
	private String msg;

	public void setMsg(String msg) {
		this.msg = msg;
		this.success = false;
	}

	public AjaxResult() {
	}

	public AjaxResult(String msg) {
		this.msg = msg;
		this.success = true;
	}

}
