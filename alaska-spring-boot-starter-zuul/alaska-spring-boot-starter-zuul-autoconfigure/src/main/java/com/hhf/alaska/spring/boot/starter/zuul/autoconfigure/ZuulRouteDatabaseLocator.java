package com.hhf.alaska.spring.boot.starter.zuul.autoconfigure;

import com.hhf.alaska.spring.boot.starter.zuul.common.ZuulRouteEntity;
import com.hhf.alaska.spring.boot.starter.zuul.common.ZuulRouteLocator;
import com.hhf.alaska.spring.boot.starter.zuul.common.ZuulRouteRuleEntity;
import com.hhf.alaska.spring.boot.starter.zuul.common.rule.IZuulRouteRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huang hong fei
 * @version 1.0.0
 */
public class ZuulRouteDatabaseLocator extends ZuulRouteLocator {

	public final static Logger logger = LoggerFactory.getLogger(ZuulRouteDatabaseLocator.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ZuulRouteDatabaseProperties zuulRouteDatabaseProperties;
	
	private List<ZuulRouteEntity> locateRouteList;

	public ZuulRouteDatabaseLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
	}

	@Override
	public Map<String, ZuulRoute> loadLocateRoute() {
		locateRouteList = new ArrayList<ZuulRouteEntity>();
		try {
			String sql = "select * from " + zuulRouteDatabaseProperties.getRouteTableName() + " where enable = true";
			locateRouteList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ZuulRouteEntity>(ZuulRouteEntity.class));
			if(!CollectionUtils.isEmpty(locateRouteList)){
				for(ZuulRouteEntity zuulRoute : locateRouteList){
					sql = "select * from " + zuulRouteDatabaseProperties.getRouteRuleTableName() + " where enable = true and route_id = ?";
					List<ZuulRouteRuleEntity> ruleList = jdbcTemplate.query(sql, new String[]{zuulRoute.getId()}, new BeanPropertyRowMapper<ZuulRouteRuleEntity>(ZuulRouteRuleEntity.class));
					zuulRoute.setRuleList(new ArrayList<IZuulRouteRule>());
					if(!CollectionUtils.isEmpty(ruleList)){
						for(ZuulRouteRuleEntity rule : ruleList){
							zuulRoute.getRuleList().add(rule);
						}
					}
				}
			}
		} catch (DataAccessException e) {
			logger.error("load zuul route from db exception", e);
		}
		return handle(locateRouteList);
	}

	@Override
	public List<IZuulRouteRule> getRules(Route route) {
		if(CollectionUtils.isEmpty(locateRouteList)){
			return null;
		}
		for(ZuulRouteEntity item : locateRouteList){
			if(item.getId().equals(route.getId())){
				return item.getRuleList();
			}
		}
		return null;
	}

}