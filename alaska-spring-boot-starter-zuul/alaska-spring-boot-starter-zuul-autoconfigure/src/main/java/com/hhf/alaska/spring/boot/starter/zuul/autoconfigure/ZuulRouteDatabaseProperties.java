package com.hhf.alaska.spring.boot.starter.zuul.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huang hong fei
 */
@ConfigurationProperties(prefix="alaska.spring.boot.starter.route")
public class ZuulRouteDatabaseProperties {

	private String routeTableName = "alaska_zuul_route";

	private String routeRuleTableName = "alaska_zuul_route_rule";

	public String getRouteTableName() {
		return routeTableName;
	}

	public void setRouteTableName(String routeTableName) {
		this.routeTableName = routeTableName;
	}

	public String getRouteRuleTableName() {
		return routeRuleTableName;
	}

	public void setRouteRuleTableName(String routeRuleTableName) {
		this.routeRuleTableName = routeRuleTableName;
	}
}
