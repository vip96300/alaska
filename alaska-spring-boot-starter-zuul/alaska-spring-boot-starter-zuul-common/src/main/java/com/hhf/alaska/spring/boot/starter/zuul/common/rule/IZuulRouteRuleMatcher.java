package com.hhf.alaska.spring.boot.starter.zuul.common.rule;

import org.springframework.cloud.netflix.zuul.filters.Route;

import java.util.List;

/**
 * @author huang hong fei
 */
public interface IZuulRouteRuleMatcher {

	Route matchingRule(Route route, List<IZuulRouteRule> rules);
}
