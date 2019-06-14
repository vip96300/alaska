package com.hhf.alaska.spring.boot.starter.zuul.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huang hong fei
 */
@ConfigurationProperties(prefix="spring.zuul.route.db")
public class ZuulRouteDatabaseProperties {

	private String tableName = "zuul_route_config";
	
	private String ruleTableName = "zuul_route_config_rule";
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRuleTableName() {
		return ruleTableName;
	}

	public void setRuleTableName(String ruleTableName) {
		this.ruleTableName = ruleTableName;
	}
	
}
