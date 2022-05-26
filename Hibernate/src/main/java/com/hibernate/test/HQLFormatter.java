package com.hibernate.test;

import org.hibernate.engine.jdbc.internal.FormatStyle;

public class HQLFormatter {
	
	public static void main(String[] args) {
        String sql = "SELECT * FROM EMPLOYEE";
        String sqlBeautify =   FormatStyle.BASIC.getFormatter().format(sql);
        System.out.println(sqlBeautify);
    }
}
