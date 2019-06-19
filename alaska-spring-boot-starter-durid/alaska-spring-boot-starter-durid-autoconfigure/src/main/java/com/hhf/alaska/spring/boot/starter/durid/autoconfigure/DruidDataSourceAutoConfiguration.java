package com.hhf.alaska.spring.boot.starter.durid.autoconfigure;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**  
 * @author huang hong fei
 */
@Configuration
@EnableConfigurationProperties(DruidProperties.class)
public class DruidDataSourceAutoConfiguration {
	
	@Primary
	@Bean(initMethod="init", destroyMethod="close")
	@ConditionalOnProperty("spring.datasource.url")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource(){
		return new DruidDataSource();
	}
}
