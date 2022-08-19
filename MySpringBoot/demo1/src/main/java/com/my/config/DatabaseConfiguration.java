package com.my.config;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration //spring설정을 하기 위해서는 꼭 Configuration 어노테이션이 있어야 한다
//application.yml파일을 application.properties파일로 변경
@PropertySource("classpath:/application.properties") //외부 파일 참조
public class DatabaseConfiguration {
	@Bean //Bean 어노테이션으로 bean 객체들 관리
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	@Bean //bean id=dataSource 
	public DataSource dataSource() throws Exception {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	@Autowired
	private ApplicationContext applicationContext;
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//mybatisConfig 폴더명을 mybatisConf로 변경
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatisConf/mybatis-config.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	
}
