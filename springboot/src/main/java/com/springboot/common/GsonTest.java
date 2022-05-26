package com.springboot.common;

import java.util.Date;
import java.util.List;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.google.gson.JsonObject;
import com.springboot.util.ConcurrentDateUtil;
import com.springboot.util.GsonUtils;

public class GsonTest {

	private static final Log LOG = LogFactory.getLog(GsonTest.class);

	public static void main(String[] args) {
		test1();
		test();
	}

	public static void test1() {
		String result = "[{PrescriptionTime:\"2020-07-18 18:23:25\"},{PrescriptionTime:\"2020-07-18 18:23:25\"}]";
		List<Prescription> decodeToArr = GsonUtils.decodeToArr(result, Prescription.class);
		for (Prescription prescription : decodeToArr) {
			String prescriptionTime = prescription.getPrescriptionTime();
			LOG.info(prescriptionTime);
			LOG.info(prescription.toString());
			LOG.info("==============");
		}
	}

	public static void test() {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("PrescriptionTime", ConcurrentDateUtil.format(new Date()));
		Prescription prescription = GsonUtils.jsonObject(jsonObject, Prescription.class);
		LOG.info(prescription + "");
	}

}
