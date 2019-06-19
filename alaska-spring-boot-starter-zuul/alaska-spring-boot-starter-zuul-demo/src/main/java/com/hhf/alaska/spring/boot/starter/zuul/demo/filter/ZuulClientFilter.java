package com.hhf.alaska.spring.boot.starter.zuul.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huang hong fei
 */
@Component
public class ZuulClientFilter extends ZuulFilter {
	
	private final Logger logger = LoggerFactory.getLogger(ZuulClientFilter.class);

	public boolean shouldFilter() {
		return true;
	}

	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("{} request to {}",request.getMethod(),request.getRequestURL());
        return true;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
