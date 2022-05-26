package com.framework.core.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

/**
 * DataSource1:ORACLE数据源
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午2:51:06
 */
@Component //注册到Spring容器中
@MapperScan(basePackages = { "com.manlong.wujing.mapper" }, //
sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DataSource1 {

	/**
	 * 配置ORACLE数据库
	 * @return
	 */
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("db1SqlSessionFactory")
	@Primary
	public SqlSessionFactory primarySqlSessionFactory(//
			@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	/**
	 * 配置事务管理器
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "db1TransactionManager")
	public DataSourceTransactionManager primaryDataSourceTransactionManager(
			@Qualifier("primaryDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 封装的数据库操作
	 * SqlSessionTemplate是一个线程安全的对象
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean(name = "db1SqlSessionTemplate")
	public SqlSessionTemplate primarySqlSessionTemplate(
			@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
