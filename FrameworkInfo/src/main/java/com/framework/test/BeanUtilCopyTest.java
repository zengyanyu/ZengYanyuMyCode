package com.framework.test;

import java.text.ParseException;
import java.util.Date;

import com.framework.domain.Brand;
import com.framework.domain.Brand2;
import com.framework.util.BeanUtils;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月24日 下午5:22:19
 */
public class BeanUtilCopyTest {

	public static void main(String[] args) throws ParseException {
		Brand brand = new Brand();
		brand.setId(1L);
		brand.setName("苹果");
		brand.setSn("PhoneXR");
		brand.setLocation("上海市陆家嘴");
		brand.setCreateDate(new Date());

		Brand2 brand2 = new Brand2();
		//BeanUtils.setProperty(brand, "name", "苹果");
		BeanUtils.copyProperties(brand, brand2);
		System.out.println(brand2);
	}

}
