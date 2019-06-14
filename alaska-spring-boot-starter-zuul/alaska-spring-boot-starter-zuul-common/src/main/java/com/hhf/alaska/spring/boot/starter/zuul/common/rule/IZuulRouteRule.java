package com.hhf.alaska.spring.boot.starter.zuul.common.rule;

import org.springframework.core.Ordered;

import java.io.Serializable;
import java.util.Map;

/**
 * @author huang hong fei
 */
public interface IZuulRouteRule extends Ordered, Serializable{
	
	boolean match(Map<String, Object> params);
	
	String getLocation();
	
}
