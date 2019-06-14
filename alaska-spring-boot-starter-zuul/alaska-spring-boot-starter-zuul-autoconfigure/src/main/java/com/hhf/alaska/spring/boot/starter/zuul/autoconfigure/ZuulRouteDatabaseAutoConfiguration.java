package com.hhf.alaska.spring.boot.starter.zuul.autoconfigure;

import com.hhf.alaska.spring.boot.starter.zuul.common.refresh.AutoRefreshRouteJob;
import com.hhf.alaska.spring.boot.starter.zuul.common.refresh.RefreshRouteService;
import com.hhf.alaska.spring.boot.starter.zuul.common.rule.DefaultZuulRouteRuleMatcher;
import com.hhf.alaska.spring.boot.starter.zuul.common.rule.IZuulRouteRuleMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;


/**
 * @author huang hong fei
 */
@Configuration
@EnableScheduling
@ConditionalOnBean(DataSource.class)
@EnableConfigurationProperties(ZuulRouteDatabaseProperties.class)
public class ZuulRouteDatabaseAutoConfiguration {

	@Autowired
	private ZuulProperties zuulProperties;

	@Autowired
	private ServerProperties server;

	@Autowired
	private DataSource dataSource;
	
	@Bean
	@ConditionalOnMissingBean(JdbcTemplate.class)
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource);
	}

	@Bean
	@ConditionalOnBean(JdbcTemplate.class)
	@ConditionalOnMissingBean(ZuulRouteDatabaseLocator.class)
	public ZuulRouteDatabaseLocator zuulRouteDatabaseLocator() {
		return new ZuulRouteDatabaseLocator(this.server.getServlet().getPath(), this.zuulProperties);
	}
	
	@Bean
	public RefreshRouteService refreshRouteService(){
		return new RefreshRouteService();
	}
	
	@Bean
	public AutoRefreshRouteJob autoRefreshRouteJob(){
		return new AutoRefreshRouteJob();
	}
	
	@Bean
	@ConditionalOnMissingBean(IZuulRouteRuleMatcher.class)
	public IZuulRouteRuleMatcher zuulRouteRuleMatcher(){
		return new DefaultZuulRouteRuleMatcher();
	}
}
