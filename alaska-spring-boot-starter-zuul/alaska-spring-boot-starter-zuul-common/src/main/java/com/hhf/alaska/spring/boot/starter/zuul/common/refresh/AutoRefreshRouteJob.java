package com.hhf.alaska.spring.boot.starter.zuul.common.refresh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * @author huang hong fei
 */
public class AutoRefreshRouteJob {
	
	private final Logger logger = LoggerFactory.getLogger(AutoRefreshRouteJob.class);
	
	@Resource
	private RefreshRouteService refreshRouteService;

	@Scheduled(cron = "${spring.zuul.route.refreshCron:0/30 * * * * ?}")
	public void run() {
		logger.info("refresh zuul route config");
		refreshRouteService.refreshRoute();
	}

}
