package com.hhf.alaska.spring.boot.starter.zuul.common.refresh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * @author huang hong fei
 */
public class AutoRefreshRouteJob {
	
	private final Logger logger = LoggerFactory.getLogger(AutoRefreshRouteJob.class);
	
	@Autowired
	private RefreshRouteService refreshRouteService;

	@Scheduled(cron = "${alaska.spring.boot.starter.maven.com.hhf.alaska.spring-boot-starter-zuul.route.refresh-cron:0/30 * * * * ?}")
	public void run() {
		logger.info("refresh maven.com.hhf.alaska.spring-boot-starter-zuul route");
		refreshRouteService.refreshRoute();
	}

}
