package com.springboot.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

/**
 * DataSource2:MySQL数据源
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午2:52:08
 */
//注册到Spring容器中
@Component
@MapperScan(basePackages = { "com.springboot.mapper1" }, //
sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DataSource2 {

	/**
	 * 配置MySQL数据库
	 * @return
	 */
	@Bean(name = "secondaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("db2SqlSessionFactory")
	public SqlSessionFactory secondarySqlSessionFactory(//
			@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	/**
	 * 配置事务管理器
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "db2TransactionManager")
	public DataSourceTransactionManager secondaryDataSourceTransactionManager(
			@Qualifier("secondaryDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 封装的数据库操作
	 * SqlSessionTemplate是一个线程安全的对象
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean(name = "db2SqlSessionTemplate")
	public SqlSessionTemplate secondarySqlSessionTemplate(
			@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
