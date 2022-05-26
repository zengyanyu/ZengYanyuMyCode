package com.springboot.test;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;

public class FreemarkerTest {

	@SuppressWarnings("deprecation")
	private static Configuration configuration = new Configuration();

	public static void main(String[] args) {
		try {
			configuration.setDirectoryForTemplateLoading(new File(""));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
